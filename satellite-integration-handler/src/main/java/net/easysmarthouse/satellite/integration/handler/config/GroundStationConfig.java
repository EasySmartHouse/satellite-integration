package net.easysmarthouse.satellite.integration.handler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
public class GroundStationConfig {

    @Bean
    public MessageChannel groundChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow groundFlow() {
        return flow -> flow.channel("groundChannel")
                //TODO send command to ground
                .handle(CharacterStreamWritingMessageHandler.stdout());
    }

}
