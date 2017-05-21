package br.com.blz.billingconsumer.listener;

import br.com.blz.billingconsumer.config.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Profile({"topic", "rpc"})
public class OrderCreateListener {

    @RabbitListener(queues = Queues.ORDER_CREATE)
    public void onOrderCreate(String message) throws InterruptedException {
        log.info("event=order_create in billing");
        Thread.sleep(100);
        log.info("updated resource=" + message);
    }

}
