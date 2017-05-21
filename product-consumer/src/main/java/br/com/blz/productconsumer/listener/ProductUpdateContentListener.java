package br.com.blz.productconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static br.com.blz.productconsumer.config.Queues.PRODUCT_UPDATE_CONTENT;

@Slf4j
@Component
@Profile({"direct", "simple-binding"})
public class ProductUpdateContentListener {

    @RabbitListener(queues = PRODUCT_UPDATE_CONTENT)
    public void onUpdateContent(String message) throws InterruptedException {

        log.info("event=update_content");

        Thread.sleep(1000);

        log.info("updated resource=" + message);
    }

}
