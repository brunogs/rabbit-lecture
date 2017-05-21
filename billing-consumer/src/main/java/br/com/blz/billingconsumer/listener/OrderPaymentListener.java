package br.com.blz.billingconsumer.listener;

import br.com.blz.billingconsumer.config.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Profile("rpc")
public class OrderPaymentListener {

    @RabbitListener(queues = Queues.ORDER_PAYMENT)
    public String onOrderPayment(String message) throws InterruptedException {
        log.info("event=order_payment in billing");
        Thread.sleep(100);
        log.info("order=" + message);
        return "order payment ok ["+ message + "]";
    }

}
