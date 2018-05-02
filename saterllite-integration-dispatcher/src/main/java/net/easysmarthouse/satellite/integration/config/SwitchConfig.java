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
public class SwitchConfig {

    @Autowired
    private ResultTransformer<String, Boolean> switchOnTransformer;

    @Autowired
    private ResultTransformer<String, Boolean> switchOffTransformer;

    @Bean
    public MessageChannel switchOnChannel() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public MessageChannel switchOffChannel() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public IntegrationFlow switchOnFlow() {
        return f -> f.channel("switchOnChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(switchOnTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Boolean) res)))
                .channel("commandResultChannel");
    }

    @Bean
    public IntegrationFlow switchOffFlow() {
        return f -> f.channel("switchOffChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(switchOnTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Boolean) res)))
                .channel("commandResultChannel");
    }

}
