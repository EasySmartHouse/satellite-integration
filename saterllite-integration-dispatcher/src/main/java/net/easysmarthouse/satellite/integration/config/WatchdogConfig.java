package net.easysmarthouse.satellite.integration.config;

import net.easysmarthouse.satellite.integration.domain.CommandResult;
import net.easysmarthouse.satellite.integration.domain.ResponseCode;
import net.easysmarthouse.satellite.integration.transformer.ResultTransformer;
import net.easysmarthouse.satellite.integration.util.BytesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executors;

@Configuration
public class WatchdogConfig {

    @Autowired
    private ResultTransformer<String, Boolean> sleepTransformer;

    @Autowired
    private ResultTransformer<String, Boolean> wakeUpTransformer;

    @Bean
    public MessageChannel sleepChannel() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public MessageChannel wakeUpChannel() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public IntegrationFlow sleepFlow() {
        return f -> f.channel("sleepChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(sleepTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Boolean) res)))
                .channel("commandResultChannel");
    }

    @Bean
    public IntegrationFlow wakeUpFlow() {
        return f -> f.channel("wakeUpChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(wakeUpTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Boolean) res)))
                .channel("commandResultChannel");
    }

}
