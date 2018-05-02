package net.easysmarthouse.satellite.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
public class StdFlowConfig {

    @Bean
    public MessageChannel stdoutChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow stdoutFlow() {
        return flow -> flow.channel("stdoutChannel")
                .handle(CharacterStreamWritingMessageHandler.stdout());
    }

}
