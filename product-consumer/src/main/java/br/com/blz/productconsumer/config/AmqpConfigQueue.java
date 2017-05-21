package br.com.blz.productconsumer.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("simple")
public class AmqpConfigQueue {

    private static final boolean DURABLE = false;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            Queue queue = new Queue(Queues.PRODUCT_UPDATE_CONTENT, DURABLE);
            amqpAdmin.declareQueue(queue);

        };
    }

}
