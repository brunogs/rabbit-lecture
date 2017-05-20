package br.com.blz.productconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static br.com.blz.productconsumer.config.Queues.CACHE_UPDATE;

@Slf4j
@Component
@Profile("fanout")
public class CacheRefreshListener {

    @RabbitListener(queues = CACHE_UPDATE)
    public void onCacheRefresh(String message) throws InterruptedException {

        log.info("event=cache_refresh");

        Thread.sleep(500);

        log.info("updated resource=" + message);
    }

}
