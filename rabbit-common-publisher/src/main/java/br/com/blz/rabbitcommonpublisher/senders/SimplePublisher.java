package br.com.blz.rabbitcommonpublisher.senders;

import br.com.blz.rabbitcommonpublisher.config.Exchanges;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.stream.IntStream.range;

@Component
@Profile("simple")
@Slf4j
public class SimplePublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SimplePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendDirectMessages() {
        log.info("Sending updates to direct exchange");

        range(0, 30).forEach(sku ->
                rabbitTemplate.convertAndSend(
                        Exchanges.PRODUCT_UPDATE,
                        "product.update.content",
                        "sku:" + sku
                )
        );
    }
}
