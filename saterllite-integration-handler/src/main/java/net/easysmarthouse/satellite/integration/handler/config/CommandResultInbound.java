package net.easysmarthouse.satellite.integration.handler.config;

import net.easysmarthouse.satellite.integration.domain.DeliveryPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

@Configuration
public class CommandResultInbound {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(rabbitConfig.workListenerContainer()))
                .transform(Transformers.fromJson(DeliveryPacket.class))
                .log()
                .filter("(headers['x-death'] != null) ? headers['x-death'][0].count <= 3: true",
                        f -> f.discardChannel("nullChannel"))
                .channel("stdoutChannel")
                .get();
    }
}
