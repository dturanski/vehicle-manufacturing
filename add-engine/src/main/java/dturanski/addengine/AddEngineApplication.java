package dturanski.addengine;

import dturanski.manufacturing.event.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Random;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AddEngineApplication {

    private static Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(AddEngineApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Event addEngine(AddEngineRequest request) {

        try {
            Thread.sleep(new Long(random.nextInt(5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("engineAdded");
        event.setData(request);

        return event;
    }

}
