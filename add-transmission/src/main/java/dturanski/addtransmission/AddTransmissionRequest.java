package dturanski.addtransmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTransmissionRequest {
    private String vin;
    private String drive;
    private String shift;
}
