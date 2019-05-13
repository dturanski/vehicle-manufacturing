package dturanski.qualityassure;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public boolean isAssemblyComplete() {

        return bodyBuilt &&
                engineAdded &&
                transmissionAdded &&
                trimAdded;
    }
}

