package br.com.blz.billingconsumer.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.amqp.core.BindingBuilder.bind;

@SuppressWarnings("Duplicates")
@Configuration
@Profile("rpc")
public class AmqpConfigRPC {

    private static final boolean DURABLE = false;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            Queue queue = new Queue(Queues.ORDER_PAYMENT, DURABLE);

            DirectExchange exchange = new DirectExchange(Exchanges.ORDER_PAYMENT);
            amqpAdmin.declareExchange(exchange);

            Binding binding = bind(queue).to(exchange).with("pay");

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);
        };
    }
}
