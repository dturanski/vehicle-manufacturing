package dturanski.placeorder.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import dturanski.manufacturing.event.Event;
import dturanski.manufacturing.util.JsonUtils;
import dturanski.placeorder.VehicleRepository;
import dturanski.placeorder.command.BuildBodyRequest;
import dturanski.placeorder.command.ManufacturingCommandHandler;
import dturanski.placeorder.domain.Vehicle;
import dturanski.placeorder.domain.VehicleSpec;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ManufacturingEventsHandlerTests {

    private VehicleRepository vehicleRepository = mock(VehicleRepository.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private ManufacturingCommandHandler manufacturingCommandHandler = mock(ManufacturingCommandHandler.class);

    ManufacturingEventsHandler manufacturingEventsHandler = new ManufacturingEventsHandler(vehicleRepository, manufacturingCommandHandler);
    @Test
    public void buildBodyEvent() throws IOException {

        String json = JsonUtils.getResourceContents("VehicleSpecs.json");
        VehicleSpec vehicleSpec = objectMapper.readValue(json, VehicleSpec.class);

        Vehicle vehicle = new Vehicle();
        vehicle.setVinNumber("123");
        vehicle.setVehicleSpec(vehicleSpec);

        when(vehicleRepository.findById(anyString())).thenReturn(Optional.of(vehicle));
        Event event = new Event();
        event.setTimestamp(System.currentTimeMillis());
        event.setType("bodyBuilt");
        event.setData(new BuildBodyRequest("123","Tahoe"));
        manufacturingEventsHandler.handleBodyBuilt(event);

    }
}
