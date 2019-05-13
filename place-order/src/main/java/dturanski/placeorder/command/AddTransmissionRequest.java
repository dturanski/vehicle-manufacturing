package dturanski.placeorder.command;

import dturanski.placeorder.domain.VehicleSpec;
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

    public AddTransmissionRequest(String vin, VehicleSpec.Transmission transmission) {
        this.vin = vin;
        this.drive = transmission.getDrive();
        this.shift = transmission.getShift();
    }

}
