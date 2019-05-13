package dturanski.addtrim;

import dturanski.manufacturing.event.Event;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AddTrimApplication {
    private static Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(AddTrimApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Message<Event> addTrim(AddTrimRequest request) {

        try {
            Thread.sleep(new Long(random.nextInt(5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("trimAdded");
        event.setData(request);
        log.info("Sending trim added event {}", event);

        return MessageBuilder.withPayload(event)
                .copyHeaders(Collections.singletonMap("event-type",event.getType())).build();
    }

}
