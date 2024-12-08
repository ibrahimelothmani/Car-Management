package ibrahim.car.management.controller;

import ibrahim.car.management.model.*;
import ibrahim.car.management.repository.AdminRepository;
import ibrahim.car.management.service.AdminService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminController(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Admin admin) {
        System.out.println("in login-admin"+admin);
        HashMap<String, Object> response = new HashMap<>();

        Admin userFromDB = adminRepository.findAdminByEmail(admin.getEmail());
        System.out.println("userFromDB+admin"+userFromDB);
        if (userFromDB == null) {
            response.put("message", "Admin not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            boolean compare = this.bCryptPasswordEncoder.matches(admin.getPassword(), userFromDB.getPassword());
            System.out.println("compare"+compare);
            if (!compare) {
                response.put("message", "admin not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

        }

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin() {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal) authenticationToken.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin logged in successfully!");
        response.put("user", principal.getName());  // You can get the user information here
        response.put("roles", authenticationToken.getAuthorities());

        return ResponseEntity.ok(response);
    }

    // Add a new admin
    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<?> addAdmin (@RequestBody Admin admin){
        HashMap<String, Object> response = new HashMap<>();
        if(adminRepository.existsByEmail(admin.getEmail())){
            response.put("MESSAGE", "EMAIL ALREADY EXIST !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
            Admin savedUser = adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);}
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Admin updateadmin(@PathVariable("id")Long id, @RequestBody Admin admin){
        admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
        Admin savedUser = adminRepository.save(admin);
        Admin newAdmin = adminService.updateAdmin(admin);
        return newAdmin;
    }

    // Get all admins
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmin();
    }

    // Get admin by ID
    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Optional<Object> admin = adminService.getAdminById(id);
        return admin.isPresent() ? ResponseEntity.ok((Admin) admin.get()) : ResponseEntity.notFound().build();
    }


    // Remove admin by ID
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> removeAdmin(@PathVariable int id) {
        Admin admin = adminService.removeAdmin(id);
        return admin != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Get all clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return adminService.getAllClient();
    }

    // Get all cars
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return adminService.getAllCar();
    }

    // Get all contacts
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return adminService.getAllContact();
    }

    // Get all purchases
    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return adminService.getAllPurchase();
    }

    // Remove a car by ID
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> removeCar(@PathVariable int id) {
        Car car = adminService.removeCar(id);
        return car != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Remove a contact by ID
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> removeContact(@PathVariable int id) {
        Contact contact = adminService.removeContact(id);
        return contact != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
