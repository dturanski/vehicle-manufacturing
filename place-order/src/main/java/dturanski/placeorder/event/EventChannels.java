package dturanski.placeorder.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface EventChannels {
    String BODY_BUILT = "bodyBuilt";
    String ENGINE_ADDED = "engineAdded";
    String TRANSMISSION_ADDED = "transmissionAdded";
    String TRIM_ADDED = "trimAdded";
    String VEHICLE_INSPECTED = "vehicleInspected";

    @Input(EventChannels.BODY_BUILT)
    MessageChannel bodyBuilt();

    @Input(EventChannels.ENGINE_ADDED)
    MessageChannel engineAdded();

    @Input(EventChannels.TRANSMISSION_ADDED)
    MessageChannel transmissionAdded();

    @Input(EventChannels.TRIM_ADDED)
    MessageChannel trimAdded();

    @Input(EventChannels.VEHICLE_INSPECTED)
    MessageChannel vehicleInspected();
}
