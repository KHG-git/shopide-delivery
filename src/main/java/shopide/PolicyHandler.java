package shopide;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shopide.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{

    private DeliveryRepository deliveryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){

        if(ordered.isMe()){
            System.out.println("##### listener Ship : " + ordered.toJson());
            Delivery delivery = new Delivery();
            delivery.setOrderId(ordered.getId());
            delivery.setStatus("SHIPPED");

            deliveryRepository.save(delivery);


        }
    }

}
