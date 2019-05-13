package dturanski.placeorder;

import dturanski.placeorder.command.BuildBodyRequest;
import dturanski.placeorder.command.ManufacturingCommandHandler;
import dturanski.placeorder.domain.Vehicle;
import dturanski.placeorder.domain.VehicleSpec;
import dturanski.placeorder.domain.VehicleStatus;

import java.util.Optional;
import java.util.UUID;

public class DefaultOrderService implements OrderService {
    private final VehicleRepository vehicleRepository;
    private final ManufacturingCommandHandler manufacturingCommandHandler;

    public DefaultOrderService(VehicleRepository vehicleRepository, ManufacturingCommandHandler manufacturingCommandHandler) {
        this.manufacturingCommandHandler = manufacturingCommandHandler;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public String  orderVehicle(VehicleSpec vehicleSpec) {
        Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(),vehicleSpec,new VehicleStatus());
        vehicleRepository.save(vehicle);
        manufacturingCommandHandler.buildBody(new BuildBodyRequest(vehicle.getVinNumber(),vehicleSpec.getModel()));
        return vehicle.getVinNumber();
    }

    @Override
    public VehicleStatus getVehicleStatus(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if (vehicle.isPresent()) {
            return vehicle.get().getVehicleStatus();
        }
        return null;
    }
}
