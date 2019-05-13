package dturanski.qualityassure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInspectionReport {
    private boolean passed;
    private Vehicle vehicle;
}
