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
public class ReadSensorConfig {

    @Autowired
    private ResultTransformer<String, Double> readValueTransformer;

    @Bean
    public MessageChannel readSensorChannel() {
        return MessageChannels.queue().datatype(String.class).get();
    }

    @Bean
    public IntegrationFlow readSensorFlow() {
        return f -> f.channel("readSensorChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(readValueTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Double) res)))
                .channel("commandResultChannel");
    }

}
