package net.easysmarthouse.satellite.integration.config;

import net.easysmarthouse.satellite.integration.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Configuration
public class CommandsSequenceConfig {

    @Autowired
    private ObjectToJsonTransformer objectToJsonTransformer;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public MessageChannel sendCommandSequenceChannel() {
        return MessageChannels.queue().datatype(CommandSequence.class).get();
    }

    @Bean
    public MessageChannel commandResultChannel() {
        return MessageChannels.queue().datatype(CommandResult.class).get();
    }

    @Bean
    public IntegrationFlow commandsSendFlow() {
        return flow -> flow.channel("sendCommandSequenceChannel")
                .enrichHeaders(h -> h.headerExpression(BusHeaders.SEQ_ID.getName(), "payload.id"))
                .split(CommandSequence.class, CommandSequence::getSequence)
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<Command, CommandType>route(Command::getCommandType, mapping -> mapping
                        .subFlowMapping(CommandType.READ_VALUE, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, String>transform(p -> p.getAddress())
                                                .channel("readSensorChannel"))))
                        .subFlowMapping(CommandType.SWITCH_ON, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, String>transform(p -> p.getAddress())
                                                .channel("switchOnChannel"))))
                        .subFlowMapping(CommandType.SWITCH_OFF, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, String>transform(p -> p.getAddress())
                                                .channel("switchOffChannel"))))
                        .subFlowMapping(CommandType.WAKE_UP, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, String>transform(p -> p.getAddress())
                                                .channel("wakeUpChannel"))))
                        .subFlowMapping(CommandType.WRITE_VALUE, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, MemoryRecord>transform(p -> new MemoryRecord(p.getAddress(), p.getData()))
                                                .channel("memoryWriteChannel"))))
                        .subFlowMapping(CommandType.SLEEP, sf -> sf
                                .publishSubscribeChannel(c -> c
                                        .subscribe(sub -> sub
                                                .<Command, String>transform(p -> p.getAddress())
                                                .channel("sleepChannel")))));
    }

    @Bean
    public IntegrationFlow commandResultFlow() {
        return flow -> flow.channel("commandResultChannel")
                .aggregate(aggregator -> aggregator
                        .outputProcessor(g -> new DeliveryPacket(g.getMessages()
                                .stream()
                                .map(message -> (CommandResult) message.getPayload())
                                .collect(Collectors.toList())))
                        .correlationStrategy(m -> m.getHeaders().get(BusHeaders.SEQ_ID.getName()))
                )
                .transform(objectToJsonTransformer)
                .channel("resultOutboundChannel");
    }

}
