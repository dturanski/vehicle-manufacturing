package dturanski.qualityassure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String vinNumber;
    private VehicleSpec vehicleSpec;
    private VehicleStatus vehicleStatus;
}
