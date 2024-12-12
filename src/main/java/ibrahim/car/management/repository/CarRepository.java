package ibrahim.car.management.repository;

import ibrahim.car.management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
