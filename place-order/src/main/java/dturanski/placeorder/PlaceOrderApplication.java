package dturanski.placeorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
public class PlaceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlaceOrderApplication.class, args);
    }
}
