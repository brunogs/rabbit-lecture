package br.com.blz.productconsumer;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("only-queue")
public class AmpqConfigIncomplete {

    private static final boolean DURABLE = false;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            Queue queue = new Queue(Queues.PRODUCT_UPDATE_CONTENT, DURABLE);
            amqpAdmin.declareQueue(queue);

        };
    }

}
