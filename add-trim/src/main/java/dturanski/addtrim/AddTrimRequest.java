package dturanski.addtrim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrimRequest {
    private String vin;
    private String level;
    private String color;
}
