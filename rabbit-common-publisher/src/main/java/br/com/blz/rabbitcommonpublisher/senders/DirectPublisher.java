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
@Profile("direct")
@Slf4j
public class DirectPublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public DirectPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendDirectMessages() {

        String exchangeName = Exchanges.PRODUCT_UPDATE;

        log.info("Sending updates to product.update.content");
        range(0, 100).forEach(sku ->
            rabbitTemplate.convertAndSend(
                    exchangeName,
                    "product.update.content",
                    "sku:" + sku
            )
        );

        log.info("Sending updates to product.update.price");
        range(0, 300).forEach(sku ->
            rabbitTemplate.convertAndSend(
                    exchangeName,
                    "product.update.price",
                    "sku:" + sku
            )
        );

        log.info("Sending updates to product.update.inventory");
        range(0, 200).forEach(sku ->
            rabbitTemplate.convertAndSend(
                    exchangeName,
                    "product.update.inventory",
                    "sku:" + sku
            )
        );
    }

}
