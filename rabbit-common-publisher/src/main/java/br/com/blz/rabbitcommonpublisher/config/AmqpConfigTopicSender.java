package br.com.blz.rabbitcommonpublisher.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("topic")
public class AmqpConfigTopicSender {

    private static final boolean DURABLE = false;
    private static final boolean AUTO_DELETE = true;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            TopicExchange exchange = new TopicExchange(Exchanges.ORDER_CREATE, DURABLE, AUTO_DELETE);
            amqpAdmin.declareExchange(exchange);

        };
    }

}
