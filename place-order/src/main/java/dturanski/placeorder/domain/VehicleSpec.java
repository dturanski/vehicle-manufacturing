package dturanski.placeorder.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSpec {

    private Transmission transmission;
    private Trim trim;
    private Engine engine;
    private String model;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Transmission {
        private String drive;
        private String shift;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Trim {
        private String level;
        private String color;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Engine {
        private String capacity;
        private String configuration;
    }
}
