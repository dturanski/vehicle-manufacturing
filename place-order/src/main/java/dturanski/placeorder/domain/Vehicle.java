package dturanski.placeorder.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

@Data
@AllArgsConstructor
@NoArgsConstructor
@KeySpace("vehicles")
public class Vehicle {
    @Id
    private String vinNumber;
    private VehicleSpec vehicleSpec;
    private VehicleStatus vehicleStatus = new VehicleStatus();
}
