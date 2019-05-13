package dturanski.placeorder;

import dturanski.placeorder.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
    List<Vehicle> findAll();
}
