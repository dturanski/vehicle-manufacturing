package dturanski.placeorder.command;

import dturanski.placeorder.domain.VehicleSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEngineRequest {
    private String vin;
    private String capacity;
    private String configuration;

    public AddEngineRequest(String vin, VehicleSpec.Engine engine) {
        this.vin = vin;
        this.capacity = engine.getCapacity();
        this.configuration = engine.getConfiguration();
    }
}
