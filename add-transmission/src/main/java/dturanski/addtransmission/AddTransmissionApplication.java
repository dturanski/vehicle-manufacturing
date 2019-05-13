package dturanski.addtransmission;

import dturanski.manufacturing.event.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Random;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AddTransmissionApplication {
    private static Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(AddTransmissionApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Event addTransmission(AddTransmissionRequest request) {

        try {
            Thread.sleep(new Long(random.nextInt(5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("transmissionAdded");
        event.setData(request);

        return event;
    }
}
