package ibrahim.car.management.service;

import ibrahim.car.management.dto.CarDto;
import ibrahim.car.management.model.Car;
import ibrahim.car.management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ResponseEntity<CarDto> getCarById(int id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(value -> ResponseEntity.ok(CarDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(CarDto::fromEntity).collect(Collectors.toList());
    }

    public CarDto addCar(CarDto carDto) {
        Car car = carDto.toEntity();
        Car savedCar = carRepository.save(car);
        return CarDto.fromEntity(savedCar);
    }

    public CarDto updateCar(int id, CarDto carDto) {
        Car car = carDto.toEntity();
        car.setId(id);
        Car updatedCar = carRepository.save(car);
        return CarDto.fromEntity(updatedCar);
    }

    public void removeCar(int id) {
        carRepository.deleteById(id);
    }
}
