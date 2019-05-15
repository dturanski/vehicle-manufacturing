package dturanski.manufacturing.event;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class MessageChannelEventPublisher implements EventPublisher {
    private final MessageChannel channel;

    public MessageChannelEventPublisher(MessageChannel channel) {
        this.channel = channel;
    }

    @Override
    public void publishEvent(Event event) {
        Message message = MessageBuilder.withPayload(event)
                .setHeader("eventType", event.getType()).build();
        channel.send(message);
    }
}
