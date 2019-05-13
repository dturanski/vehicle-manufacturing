package dturanski.placeorder;

import dturanski.placeorder.domain.VehicleSpec;
import dturanski.placeorder.domain.VehicleStatus;

public interface OrderService {
    String orderVehicle(VehicleSpec vehicleSpec);
    VehicleStatus getVehicleStatus(String vin);
}
