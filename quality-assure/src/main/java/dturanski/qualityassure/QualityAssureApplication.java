package dturanski.qualityassure;

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
public class QualityAssureApplication {
    private static Random random = new Random();
    private static int PASS_RATE = 90;
    public static void main(String[] args) {
        SpringApplication.run(QualityAssureApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Message<Event> inspectVehicle(Vehicle vehicle) {

        try {
            Thread.sleep(new Long(random.nextInt(5000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("vehicleInspected");


        boolean passed = true;
        int num = random.nextInt(100);

        if (num > PASS_RATE) {
            passed = false;
        }

        vehicle.getVehicleStatus().setInspectionPassed(true);
        event.setData(vehicle);

        return MessageBuilder.withPayload(event)
                .copyHeaders(Collections.singletonMap("event-type",event.getType())).build();
    }
}
