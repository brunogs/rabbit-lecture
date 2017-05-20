package br.com.blz.inventoryconsumer.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Profile("direct")
@Configuration
public class AmqpConfigDirect {

    private static final boolean DURABLE = false;

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setConcurrentConsumers(16);
        containerFactory.setMaxConcurrentConsumers(16);
        return containerFactory;
    }

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {
            Queue queue = new Queue(Queues.PRODUCT_UPDATE_INVENTORY, DURABLE);
            DirectExchange exchange = new DirectExchange(Exchanges.PRODUCT_UPDATE);

            Binding binding = bind(queue).to(exchange).withQueueName();

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);
        };
    }
}
