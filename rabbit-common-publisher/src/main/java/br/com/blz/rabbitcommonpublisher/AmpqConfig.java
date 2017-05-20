package br.com.blz.rabbitcommonpublisher;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmpqConfig {

    @Bean
    InitializingBean setupQueues(AmqpAdmin amqpAdmin) {
        return () -> {

            Exchange exchange = new DirectExchange(Exchanges.PRODUCT_UPDATE);
            amqpAdmin.declareExchange(exchange);

        };
    }

}
