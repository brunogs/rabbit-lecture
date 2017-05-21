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
@Profile("topic")
@Slf4j
public class TopicPublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public TopicPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendTopicMessages() {

        String exchangeName = Exchanges.ORDER_CREATE;

        String routingKey = "new.order.create";
        //String routingKey = "order.create.resend";
        //String routingKey = "new.order.create.offer";

        log.info("notify " + routingKey);
        range(0, 10).forEach(sku ->

                rabbitTemplate.convertAndSend(
                        exchangeName,
                        routingKey,
                        "sku:" + sku
                )
        );

    }
}
