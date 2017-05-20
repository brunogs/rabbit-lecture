package br.com.blz.inventoryconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static br.com.blz.inventoryconsumer.config.Queues.CACHE_UPDATE;


@Slf4j
@Component
@Profile("fanout")
public class CacheUpdateListener {

    @RabbitListener(queues = CACHE_UPDATE)
    public void onCacheUpdate(String message) throws InterruptedException {

        log.info("event=cache_update");

        Thread.sleep(100);

        log.info("updated resource=" + message);
    }

}
