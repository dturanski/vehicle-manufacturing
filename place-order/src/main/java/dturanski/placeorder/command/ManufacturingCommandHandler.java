package dturanski.placeorder.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dturanski.placeorder.domain.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collections;

@EnableBinding(Source.class)
@Slf4j
public class ManufacturingCommandHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public MessageChannel request() {
        return new DirectChannel();
    }


    public void buildBody(BuildBodyRequest buildBodyRequest) {
        log.info("Sending build body request {}", buildBodyRequest);
        request().send(MessageBuilder.withPayload(buildBodyRequest)
                .setHeader("task.name", "build-body")
                .build());
    }

    public void addEngine(AddEngineRequest addEngineRequest) {
        log.info("Sending add engine request {}", addEngineRequest);
        request().send(MessageBuilder.withPayload(addEngineRequest)
                .setHeader("task.name", "add-engine")
                .withPayload(addEngineRequest).build());

    }


    public void addTransmission(AddTransmissionRequest addTransmissionRequest) {
        log.info("Sending add transmission request {}", addTransmissionRequest);
        request().send(MessageBuilder.withPayload(addTransmissionRequest)
                .setHeader("task.name", "add-transmission")
                .build());
    }


    public void addTrim(AddTrimRequest addTrimRequest) {
        log.info("Sending add trim request {}", addTrimRequest);
        request().send(MessageBuilder.withPayload(addTrimRequest)
                .setHeader("task.name", "add-trim")
                .build());
    }


    public void inspectVehicle(Vehicle vehicle) {
        log.info("Sending inspect vehicle request {}", vehicle);
        request().send(MessageBuilder.withPayload(vehicle)
                .setHeader("task.name", "inspect-vehicle")
                .withPayload(vehicle).build());
    }

    @Bean
    IntegrationFlow transformToTaskLaunchRequest() {
        return IntegrationFlows.from(request())
                .transform((Message m) -> {
                    DataFlowTaskLaunchRequest taskLaunchRequest = new DataFlowTaskLaunchRequest();
                    taskLaunchRequest.setTaskName(m.getHeaders().get("task.name").toString());
                    try {
                        taskLaunchRequest.setCommandlineArguments(
                                Collections.singletonList(
                                "request="+objectMapper.writeValueAsString(m.getPayload())));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                    return taskLaunchRequest;
                })
                .channel(Source.OUTPUT).get();
    }

}
