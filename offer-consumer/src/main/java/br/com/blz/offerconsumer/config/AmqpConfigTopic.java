package br.com.blz.offerconsumer.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.amqp.core.BindingBuilder.bind;

@SuppressWarnings("Duplicates")
@Configuration
@Profile("topic")
public class AmqpConfigTopic {

    private static final boolean DURABLE = false;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            Queue queue = new Queue(Queues.ORDER_CREATE, DURABLE);
            TopicExchange exchange = new TopicExchange(Exchanges.ORDER_CREATE);

            Binding binding = bind(queue).to(exchange).with("*.order.*.offer");

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);
        };
    }
}
