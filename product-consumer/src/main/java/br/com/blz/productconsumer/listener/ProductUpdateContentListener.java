package br.com.blz.productconsumer.listener;

import br.com.blz.productconsumer.config.Queues;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductUpdateContentListener {

    @RabbitListener(queues = Queues.PRODUCT_UPDATE_CONTENT)
    public void onUpdateContent(String message) throws InterruptedException {

        System.out.println("event=update_content");

        Thread.sleep(1000);

        System.out.println("updated resource=" + message);
    }

}
