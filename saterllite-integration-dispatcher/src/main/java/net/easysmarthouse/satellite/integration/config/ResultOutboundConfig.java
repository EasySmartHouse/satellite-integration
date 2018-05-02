package net.easysmarthouse.satellite.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ResultOutboundConfig {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Bean
    public MessageChannel resultOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow toOutboundQueueFlow() {
        return IntegrationFlows.from("resultOutboundChannel")
                .handle(Amqp.outboundAdapter(rabbitConfig.worksRabbitTemplate()))
                .get();
    }

}
