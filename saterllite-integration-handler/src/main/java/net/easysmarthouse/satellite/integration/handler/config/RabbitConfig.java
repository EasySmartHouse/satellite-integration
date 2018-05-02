package net.easysmarthouse.satellite.integration.handler.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;


@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    Exchange resultExchange() {
        return ExchangeBuilder.topicExchange("commands.result.exchange")
                .durable(true)
                .build();
    }

    @Bean
    public Queue resultQueue() {
        return QueueBuilder.durable("commands.result.queue")
                .withArgument("x-dead-letter-exchange", resultDlExchange().getName())
                .build();
    }

    @Bean
    Binding resultBinding() {
        return BindingBuilder
                .bind(resultQueue())
                .to(resultExchange())
                .with("#")
                .noargs();
    }

    @Bean
    Exchange resultDlExchange() {
        return ExchangeBuilder
                .topicExchange("commands.result.exchange.dl")
                .durable(true)
                .build();
    }

    @Bean
    public Queue resultDLQueue() {
        return QueueBuilder
                .durable("commands.result.queue.dl")
                .withArgument("x-message-ttl", 20000)
                .withArgument("x-dead-letter-exchange", resultExchange().getName())
                .build();
    }

    @Bean
    Binding resultDlBinding() {
        return BindingBuilder
                .bind(resultDLQueue())
                .to(resultDlExchange())
                .with("#")
                .noargs();
    }


    @Bean
    public SimpleMessageListenerContainer workListenerContainer() {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(rabbitConnectionFactory);
        container.setQueues(resultQueue());
        container.setConcurrentConsumers(2);
        container.setDefaultRequeueRejected(false);
        //container.setAdviceChain(new Advice[]{interceptor()});
        return container;
    }

    @Bean
    RetryOperationsInterceptor interceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
                .backOffOptions(1000, 3, 60000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

}
