package br.com.blz.rabbitcommonpublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.stream.IntStream.range;

@Component
public class SimplePublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public SimplePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendMessages() {
        System.out.println("Sending updates to direct exchange");
        range(0, 30).forEach(sku -> {
                rabbitTemplate.convertAndSend(Exchanges.PRODUCT_UPDATE, null, "sku");
        });
    }
}
