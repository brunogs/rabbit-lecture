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
@Profile("fanout")
@Slf4j
public class FanoutPublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendFanoutMessages() {

        String exchangeName = Exchanges.CACHE_UPDATE;

        log.info("notify cache.update");
        range(0, 10).forEach(sku ->

                rabbitTemplate.convertAndSend(
                        exchangeName,
                        "",
                        "sku:" + sku
                )
        );

    }
}
