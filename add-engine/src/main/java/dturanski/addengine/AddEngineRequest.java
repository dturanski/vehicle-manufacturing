package dturanski.addengine;

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
}
