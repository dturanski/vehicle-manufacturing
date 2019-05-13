package dturanski.qualityassure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleStatus {
    private boolean bodyBuilt;
    private boolean engineAdded;
    private boolean transmissionAdded;
    private boolean trimAdded;
    private boolean inspectionPassed;

    public boolean isAssemblyComplete() {

        return bodyBuilt &&
                engineAdded &&
                transmissionAdded &&
                trimAdded;
    }
}

