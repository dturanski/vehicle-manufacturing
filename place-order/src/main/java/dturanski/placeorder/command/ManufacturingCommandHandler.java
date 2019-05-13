package dturanski.placeorder.command;

import dturanski.placeorder.domain.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding({CommandChannels.class})
@Slf4j
public class ManufacturingCommandHandler {

    @Autowired
    CommandChannels commandChannels;

    public void buildBody(BuildBodyRequest buildBodyRequest) {
        log.info("Sending build body request {}", buildBodyRequest);
        commandChannels.buildBody().send(MessageBuilder.withPayload(buildBodyRequest).build());
    }

    public void addEngine(AddEngineRequest addEngineRequest) {
        log.info("Sending add engine request {}", addEngineRequest);
        commandChannels.addEngine().send(MessageBuilder.withPayload(addEngineRequest).build());

    }


    public void addTransmission(AddTransmissionRequest addTransmissionRequest) {
        log.info("Sending add transmission request {}", addTransmissionRequest);
        commandChannels.addTransmission().send(MessageBuilder.withPayload(addTransmissionRequest).build());
    }


    public void addTrim(AddTrimRequest addTrimRequest) {
        log.info("Sending add trim request {}", addTrimRequest);
        commandChannels.addTrim().send(MessageBuilder.withPayload(addTrimRequest).build());
    }


    public void inspectVehicle(Vehicle vehicle) {
        log.info("Sending inspect vehicle request {}", vehicle);
        commandChannels.inspectVehicle().send(MessageBuilder.withPayload(vehicle).build());
    }

}
