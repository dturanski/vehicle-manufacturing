package dturanski.manufacturing.event;

import lombok.Data;

@Data
public class Event {
    private String type;
    private long timestamp;
    private Object data;
}
