package ibrahim.car.management.service;

import ibrahim.car.management.model.Car;
import ibrahim.car.management.model.Client;
import ibrahim.car.management.model.Purchase;
import ibrahim.car.management.model.PurchaseRq;
import ibrahim.car.management.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final ClientService clientService;
    private final CarService carService;

    public PurchaseService(PurchaseRepository purchaseRepository, ClientService clientService, CarService carService) {
        this.purchaseRepository = purchaseRepository;
        this.clientService = clientService;
        this.carService = carService;
    }

    public Purchase addPurchase(PurchaseRq purchase){
        Optional<Car> car = Optional.ofNullable(carService.getCarById(purchase.getIdCar()));
        Optional<Client> client = clientService.getClientById(purchase.getIdClient());
        if(client.isPresent() && car.isPresent()){
            if(!this.purchaseRepository.existsByClientIdAndCarId(purchase.getIdClient(),purchase.getIdCar()))
                return purchaseRepository.save(new Purchase());
            else return null;
        }
        else
            return null;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> GetCarByClient(Long id){
        return purchaseRepository.findByClientId(id);
    }
    public Purchase getPurchaseById(Long id) {
        return (Purchase) purchaseRepository.findById(id).orElse(null);
    }
}
