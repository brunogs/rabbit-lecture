package br.com.blz.productconsumer.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("simple-binding")
public class AmqpConfigWithBinding {

    private static final boolean DURABLE = false;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            Queue queue = new Queue(Queues.PRODUCT_UPDATE_CONTENT, DURABLE);
            Exchange exchange = new DirectExchange(Exchanges.PRODUCT_UPDATE);

            Binding binding = BindingBuilder.bind(queue).to(exchange).with(Queues.PRODUCT_UPDATE_CONTENT).noargs();

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);

        };
    }

}
