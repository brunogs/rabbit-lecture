package br.com.blz.rabbitcommonpublisher.senders;

import br.com.blz.rabbitcommonpublisher.config.Exchanges;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("rpc")
@Slf4j
public class RPCPublisher {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RPCPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void requestPayment() {

        log.info("requesting payment");

        String result = (String) rabbitTemplate.convertSendAndReceive(
                Exchanges.ORDER_PAYMENT,
                "pay",
                "123"
        );

        /*rabbitTemplate.convertAndSend(
                Exchanges.ORDER_PAYMENT,
                "pay",
                "123"
        );*/

        log.info("result: {}", result);

    }

}
