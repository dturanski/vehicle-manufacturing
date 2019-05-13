package dturanski.buildbody;

import dturanski.manufacturing.event.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collections;
import java.util.Random;

@SpringBootApplication
@EnableBinding(Processor.class)
public class BuildBodyApplication {

    private static Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(BuildBodyApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Message<Event> buildBody(BuildBodyRequest request) {

        try {
            Thread.sleep(new Long(random.nextInt(5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("bodyBuilt");
        event.setData(request);

        return MessageBuilder.withPayload(event)
                .copyHeaders(Collections.singletonMap("event-type",event.getType())).build();
    }

}
