package dturanski.qualityassure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private String vinNumber;
    private VehicleSpec vehicleSpec;
    private VehicleStatus vehicleStatus;
}
