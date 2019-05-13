package dturanski.placeorder.config;

import dturanski.placeorder.DefaultOrderService;
import dturanski.placeorder.OrderService;
import dturanski.placeorder.VehicleRepository;
import dturanski.placeorder.command.ManufacturingCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceOrderConfiguration {
    @Bean
    public OrderService orderService(VehicleRepository vehicleRepository, ManufacturingCommandHandler manufacturingCommandHandler) {
        return new DefaultOrderService(vehicleRepository, manufacturingCommandHandler);
    }



}
