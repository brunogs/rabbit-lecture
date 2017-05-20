package br.com.blz.priceconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static br.com.blz.priceconsumer.config.Queues.PRODUCT_UPDATE_PRICE;

@Slf4j
@Profile("direct")
@Component
public class PriceUpdateListener {

    @RabbitListener(queues = PRODUCT_UPDATE_PRICE)
    public void onUpdatePrice(String message) throws InterruptedException {
        log.info("event=update_price");
        Thread.sleep(500);
        log.info("updated resource=" + message);
    }

}
