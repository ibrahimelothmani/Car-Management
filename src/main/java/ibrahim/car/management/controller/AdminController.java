package ibrahim.car.management.controller;

import ibrahim.car.management.dto.AdminDto;
import ibrahim.car.management.service.AdminService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody AdminDto adminDto) {
        return adminService.loginAdmin(adminDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody AdminDto adminDto, KeycloakAuthenticationToken token) {
        String username = token.getName();
        String role = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("unknown");

        // Log the roles for debugging
        System.out.println("User Role: " + role);

        if (adminService.existsByEmail(adminDto.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Proceed with creating admin
        adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        AdminDto savedAdmin = adminService.addAdmin(adminDto);

        Map<String, Object> response = new HashMap<>();
        response.put("admin", savedAdmin);
        response.put("createdBy", username);
        response.put("userRole", role);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable("id") int id, @RequestBody AdminDto adminDto, KeycloakAuthenticationToken token) {
        // Example: Check user role before allowing update
        boolean isAdmin = token.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        AdminDto updatedAdmin = adminService.updateAdmin(id, adminDto);
        if (updatedAdmin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDto>> getAllAdmins(KeycloakAuthenticationToken token) {
        // Example: Log user information
        String username = token.getName();
        System.out.println("User accessing admins: " + username);

        List<AdminDto> adminDtos = new ArrayList<>(adminService.getAllAdmin());
        return ResponseEntity.ok(adminDtos);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable int id, KeycloakAuthenticationToken token) {
        String username = token.getName();
        System.out.println("User fetching admin by ID: " + username);

        return adminService.getAdminById(id);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> removeAdmin(@PathVariable int id, KeycloakAuthenticationToken token) {
        String username = token.getName();
        System.out.println("User deleting admin: " + username);

        adminService.removeAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
