package dturanski.placeorder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class VehicleStatus {
    private boolean bodyBuilt;
    private boolean engineAdded;
    private boolean transmissionAdded;
    private boolean trimAdded;
    private boolean inspectionPassed;

    @JsonIgnore
    public boolean isAssemblyComplete() {
        return isBodyBuilt() &&
                isEngineAdded() &&
                isTransmissionAdded() &&
                isTrimAdded();
    }
}

