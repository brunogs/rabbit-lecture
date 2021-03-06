package br.com.blz.rabbitcommonpublisher.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"direct", "simple"})
public class AmqpConfigSimpleSender {

    private static final boolean DURABLE = false;
    private static final boolean AUTO_DELETE = true;

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            Exchange exchange = new DirectExchange(Exchanges.PRODUCT_UPDATE, DURABLE, AUTO_DELETE);
            amqpAdmin.declareExchange(exchange);

        };
    }

}
