package dturanski.buildbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildBodyRequest {
    private String vin;
    private String model;
}
