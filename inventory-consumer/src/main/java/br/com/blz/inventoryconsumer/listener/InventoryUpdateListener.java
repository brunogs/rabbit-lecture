package br.com.blz.inventoryconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static br.com.blz.inventoryconsumer.config.Queues.PRODUCT_UPDATE_INVENTORY;

@Slf4j
@Component
@Profile("direct")
public class InventoryUpdateListener {

    @RabbitListener(queues = PRODUCT_UPDATE_INVENTORY)
    public void onUpdateInventory(String message) throws InterruptedException {
        log.info("event=update_inventory");
        Thread.sleep(1000);
        log.info("updated resource=" + message);
    }

}
