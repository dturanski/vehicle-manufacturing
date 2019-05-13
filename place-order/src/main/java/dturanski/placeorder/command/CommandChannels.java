package dturanski.placeorder.command;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CommandChannels {

    String BUILD_BODY = "buildBody";
    String ADD_ENGINE = "addEngine";
    String ADD_TRANSMISSION = "addTransmission";
    String ADD_TRIM = "addTrim";
    String INSPECT_VEHICLE = "inspectVehicle";


    @Output(CommandChannels.BUILD_BODY)
    MessageChannel buildBody();

    @Output(CommandChannels.ADD_ENGINE)
    MessageChannel addEngine();

    @Output(CommandChannels.ADD_TRANSMISSION)
    MessageChannel addTransmission();


    @Output(CommandChannels.ADD_TRIM)
    MessageChannel addTrim();

    @Output(CommandChannels.INSPECT_VEHICLE)
    MessageChannel inspectVehicle();
}
