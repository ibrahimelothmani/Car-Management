package ibrahim.car.management.controller;

import ibrahim.car.management.dto.CarDto;
import ibrahim.car.management.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtos = new ArrayList<>(carService.getAllCars());
        return ResponseEntity.ok(carDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        CarDto savedCar = carService.addCar(carDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") int id, @RequestBody CarDto carDto) {
        CarDto updatedCar = carService.updateCar(id, carDto);
        if (updatedCar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCar(@PathVariable int id) {
        carService.removeCar(id);
        return ResponseEntity.noContent().build();
    }
}
