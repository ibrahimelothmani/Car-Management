package ibrahim.car.management.service;

import ibrahim.car.management.dto.*;
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

    public List<AdminDto> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Optional<Object> getAdminById(int id){
        return adminRepository.findById(id);
    }
    public List<ClientDto> getAllClient(){
        return clientRepository.findAll();
    }
    public List<CarDto> getAllCar(){
        return carRepository.findAll();
    }
    public List<ContactDto> getAllContact(){
        return contactRepository.findAll();
    }
    public List<PurchaseDto> getAllPurchase(){
        return purchaseRepository.findAll();
    }
    public AdminDto addAdmin(AdminDto admin){
        return adminRepository.save(admin);
    }

    public AdminDto updateAdmin(AdminDto admin){
        return adminRepository.save(admin);
    }

    public AdminDto removeAdmin(int id){
        Object admin = adminRepository.findById(id).orElse(null);
        if(admin!=null){
            adminRepository.delete(id);
        }
        return (AdminDto) admin;
    }
    public CarDto updateCar(int id){
        return CarDto.fromEntity(carRepository.save(id));
    }
    public CarDto removeCar(int id){
        CarDto car = (CarDto) carRepository.findById(id).orElse(null);
        if(car!=null){
            carRepository.delete(car.toEntity());
        }
        return car;
    }

    public ContactDto removeContact(int id){
        ContactDto contact = (ContactDto) contactRepository.findById(id).orElse(null);
        if(contact!=null){
            contactRepository.delete(contact.toEntity());
        }
        return contact;
    }
}
