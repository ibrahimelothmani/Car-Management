package ibrahim.car.management.service;

import ibrahim.car.management.model.*;
import ibrahim.car.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ContactRepository contactRepository;
    private final PurchaseRepository purchaseRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository, ClientRepository clientRepository, CarRepository carRepository, ContactRepository contactRepository, ibrahim.car.management.repository.PurchaseRepository purchaseRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.contactRepository = contactRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Optional<Object> getAdminById(int id){
        return adminRepository.findById(id);
    }
    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }
    public List<Car> getAllCar(){
        return carRepository.findAll();
    }
    public List<Contact> getAllContact(){
        return contactRepository.findAll();
    }
    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAll();
    }
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin removeAdmin(int id){
        Admin admin = (Admin) adminRepository.findById(id).orElse(null);
        if(admin!=null){
            adminRepository.delete(admin);
        }
        return admin;
    }
    public Car updateCar(int id){
        return carRepository.save(id);
    }
    public Car removeCar(int id){
        Car car = (Car) carRepository.findById(id).orElse(null);
        if(car!=null){
            carRepository.delete(car);
        }
        return car;
    }

    public Contact removeContact(int id){
        Contact contact = (Contact) contactRepository.findById(id).orElse(null);
        if(contact!=null){
            contactRepository.delete(contact);
        }
        return contact;
    }
}
