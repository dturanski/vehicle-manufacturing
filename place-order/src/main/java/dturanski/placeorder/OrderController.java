package dturanski.placeorder;

import dturanski.placeorder.domain.VehicleSpec;
import dturanski.placeorder.domain.VehicleStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public  OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/orders")
    public Map<String,String> orderVehicle(@RequestBody VehicleSpec vehicleSpec) {
        return Collections.singletonMap("vin", orderService.orderVehicle(vehicleSpec));
    }

    @GetMapping("/orders/{vin}")
    public VehicleStatus getStatus(@PathVariable String vin) {
        VehicleStatus vehicleStatus =  orderService.getVehicleStatus(vin);
        if (vehicleStatus == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle " + vin + " does not exist");
        }
        return vehicleStatus;
    }
}
