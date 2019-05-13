package dturanski.placeorder.command;

import dturanski.placeorder.domain.VehicleSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrimRequest {
    private String vin;
    private String level;
    private String color;

    public AddTrimRequest(String vin, VehicleSpec.Trim trim) {
        this.vin = vin;
        this.color = trim.getColor();
        this.level = trim.getLevel();
    }
}
