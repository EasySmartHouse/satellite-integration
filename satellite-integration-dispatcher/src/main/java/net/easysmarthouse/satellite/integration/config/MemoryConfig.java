package net.easysmarthouse.satellite.integration.config;

import net.easysmarthouse.satellite.integration.domain.CommandResult;
import net.easysmarthouse.satellite.integration.domain.MemoryRecord;
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
public class MemoryConfig {

    @Autowired
    private ResultTransformer<MemoryRecord, Boolean> writeValueTransformer;

    @Bean
    public MessageChannel memoryWriteChannel() {
        return MessageChannels.queue().datatype(MemoryRecord.class).get();
    }

    @Bean
    public IntegrationFlow memoryWriteFlow() {
        return f -> f.channel("memoryWriteChannel")
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .transform(writeValueTransformer)
                .transform(res -> new CommandResult(ResponseCode.OK, BytesUtil.toByteArray((Boolean) res)))
                .channel("commandResultChannel");
    }

}
