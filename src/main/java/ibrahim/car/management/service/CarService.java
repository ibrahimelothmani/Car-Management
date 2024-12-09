package ibrahim.car.management.service;


import ibrahim.car.management.model.Car;
import ibrahim.car.management.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        carRepository.save(car.getId());
    }

    public Car getCarById(int id) {
        return (Car) carRepository.findById(id).orElse(null);
    }

    public void updateCar(Car car) {
        carRepository.save(car.getId());
    }

    public void deleteCar(int id) {
        Car car = (Car) carRepository.findById(id).orElse(null);
        if (car!= null) {
            carRepository.delete(car);
        }
    }
}
