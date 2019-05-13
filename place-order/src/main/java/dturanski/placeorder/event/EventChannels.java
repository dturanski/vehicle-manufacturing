package dturanski.placeorder.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface EventChannels {
    String EVENTS = "events";
    @Input(EventChannels.EVENTS)
    MessageChannel events();
}
