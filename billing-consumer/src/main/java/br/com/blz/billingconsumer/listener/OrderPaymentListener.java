package br.com.blz.billingconsumer.listener;

import br.com.blz.billingconsumer.config.Queues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static br.com.blz.billingconsumer.config.Exchanges.ORDER_PAYMENT;


@Slf4j
@Component
@Profile("rpc")
public class OrderPaymentListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Queues.ORDER_PAYMENT),
            exchange = @Exchange(
                    value = ORDER_PAYMENT,
                    durable = "true",
                    ignoreDeclarationExceptions = "true"
            ),
            key = "pay"
    ))
    /*@SendTo("order.create/new.order.create")*/
    public String onOrderPayment(String message) throws InterruptedException {
        log.info("event=order_payment in billing");
        Thread.sleep(100);
        log.info("order=" + message);
        return "order payment ok ["+ message + "]";
    }

}
