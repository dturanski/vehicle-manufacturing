package dturanski.placeorder.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import dturanski.manufacturing.event.Event;
import dturanski.placeorder.VehicleRepository;
import dturanski.placeorder.command.*;
import dturanski.placeorder.domain.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding({EventChannels.class})
@Slf4j
public class ManufacturingEventsHandler {

    private final VehicleRepository vehicleRepository;
    private final ManufacturingCommandHandler commandHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ManufacturingEventsHandler(VehicleRepository vehicleRepository, ManufacturingCommandHandler manufacturingCommandHandler) {
        this.vehicleRepository = vehicleRepository;
        this.commandHandler = manufacturingCommandHandler;
    }

    @StreamListener(value = EventChannels.EVENTS, condition = "headers['event-type']=='bodyBuilt'")
    public void handleBodyBuilt(Event event) {
        log.info("received body built event {}", event);

        BuildBodyRequest buildBodyRequest = objectMapper.convertValue(event.getData(), BuildBodyRequest.class);
        Vehicle vehicle = getVehicle(buildBodyRequest.getVin());
        vehicle.getVehicleStatus().setBodyBuilt(true);
        vehicleRepository.save(vehicle);
        commandHandler.addEngine(new AddEngineRequest(vehicle.getVinNumber(), vehicle.getVehicleSpec().getEngine()));
        commandHandler.addTransmission(new AddTransmissionRequest(vehicle.getVinNumber(), vehicle.getVehicleSpec().getTransmission()));
        commandHandler.addTrim(new AddTrimRequest(vehicle.getVinNumber(), vehicle.getVehicleSpec().getTrim()));
    }

    @StreamListener(value = EventChannels.EVENTS, condition = "headers['event-type']=='engineAdded'")
    public void handleEngineAdded(Event event) {
        log.info("received engine added event {}", event);

        AddEngineRequest addEngineRequest = objectMapper.convertValue(event.getData(),AddEngineRequest.class);
        Vehicle vehicle = getVehicle(addEngineRequest.getVin());
        vehicle.getVehicleStatus().setEngineAdded(true);
        vehicleRepository.save(vehicle);
        inspectVehicleIfAssemblyComplete(vehicle);
    }

    @StreamListener(value = EventChannels.EVENTS, condition = "headers['event-type']=='transmissionAdded'")
    public void handleTransmissionAdded(Event event) {
        log.info("received transmission added event {}", event);
        AddTransmissionRequest addTransmissionRequest = objectMapper.convertValue(event.getData(),AddTransmissionRequest.class);
        Vehicle vehicle = getVehicle(addTransmissionRequest.getVin());
        vehicle.getVehicleStatus().setTransmissionAdded(true);
        vehicleRepository.save(vehicle);
        inspectVehicleIfAssemblyComplete(vehicle);

    }

    @StreamListener(value = EventChannels.EVENTS, condition = "headers['event-type']=='trimAdded'")
    public void handleTrimAdded(Event event) {
        log.info("received trim added event {}", event);

        AddTrimRequest addTrimRequest = objectMapper.convertValue(event.getData(),AddTrimRequest.class);
        Vehicle vehicle = getVehicle(addTrimRequest.getVin());
        vehicle.getVehicleStatus().setTrimAdded(true);
        vehicleRepository.save(vehicle);
        inspectVehicleIfAssemblyComplete(vehicle);
    }

    @StreamListener(value = EventChannels.EVENTS, condition = "headers['event-type']=='vehicleInspected'")
    public void handleVehicleInspected(Event event) {
        log.info("received vehicle inspected event {}", event);
        Vehicle vehicle = objectMapper.convertValue(event.getData(),Vehicle.class);
        vehicleRepository.save(vehicle);
        log.info("Quality inspection {} for vehicle {}", vehicle.getVehicleStatus().isInspectionPassed() ? "passed" : "failed", vehicle);
    }

    private Vehicle getVehicle(String vin) {
        Vehicle vehicle = vehicleRepository.findById(vin).orElseThrow(() ->
                new RuntimeException("Invalid vehicle id " + vin));
        return vehicle;
    }

    private void inspectVehicleIfAssemblyComplete(Vehicle vehicle) {

        if (vehicle.getVehicleStatus().isAssemblyComplete()) {
            log.debug("assembly complete for {}", vehicle);
            commandHandler.inspectVehicle(vehicle);
        } else {
            log.debug("assembly not complete for {}", vehicle);
        }
    }


}
