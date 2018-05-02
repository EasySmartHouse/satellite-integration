package net.easysmarthouse.satellite.integration.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    TopicExchange commandsResultExchange() {
        return new TopicExchange("commands.result.exchange", true, false);
    }

    @Bean
    public RabbitTemplate worksRabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setExchange("commands.result.exchange");
        template.setRoutingKey("result");
        template.setConnectionFactory(rabbitConnectionFactory);
        return template;
    }

}
