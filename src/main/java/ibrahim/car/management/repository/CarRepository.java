package ibrahim.car.management.repository;

import ibrahim.car.management.dto.CarDto;
import ibrahim.car.management.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository {

    Optional<Object> findById(int id);
    void delete(Car car);
    Car save(int id);
    List<CarDto> findAll();
}
