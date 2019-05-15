package dturanski.buildbody;

import com.fasterxml.jackson.databind.ObjectMapper;
import dturanski.manufacturing.event.Event;
import dturanski.manufacturing.event.EventPublisher;
import dturanski.manufacturing.event.MessageChannelEventPublisher;
import dturanski.manufacturing.util.ArgUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Random;

@Configuration
@EnableBinding(Source.class)
@EnableTask
@Slf4j
public class TaskConfiguration {

    private static Random random = new Random();

    @Bean
    public EventPublisher eventPublisher(Source source) {
        return new MessageChannelEventPublisher(source.output());
    }

    @Bean
    public CommandLineRunner commandLineRunner(EventPublisher eventPublisher) {
        ObjectMapper objectMapper = new ObjectMapper();

        return args -> {

            Map<String,String> argsMap = ArgUtils.argsAsMap(args);

            int maxSleepMs = argsMap.get("maxSleepMs") == null? 5000 :
                    Integer.valueOf(argsMap.get("maxSleepMs"));

            if (argsMap.get("request") == null) {
                throw new IllegalArgumentException("request is missing");
            }

            String json = argsMap.get("request");
            log.info("received request: {}", json);

            if (maxSleepMs > 0) {
                Thread.sleep(random.nextInt(maxSleepMs));
            }

            BuildBodyRequest buildBodyRequest = objectMapper.readValue(json,BuildBodyRequest.class);
            log.info("Body Built for vin {} specs {}", buildBodyRequest);

            Event event = new Event();
            event.setType("bodyBuilt");
            event.setData(buildBodyRequest);
            eventPublisher.publishEvent(event);
        };
    }
}