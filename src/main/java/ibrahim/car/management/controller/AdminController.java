package ibrahim.car.management.controller;

import ibrahim.car.management.dto.AdminDto;
import ibrahim.car.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody AdminDto adminDto) {
        if (adminService.existsByEmail(adminDto.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        AdminDto savedAdmin = adminService.addAdmin(adminDto);
        Map<String, Object> response = new HashMap<>();
        response.put("admin", savedAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable("id") int id, @RequestBody AdminDto adminDto) {
        AdminDto updatedAdmin = adminService.updateAdmin(id, adminDto);
        if (updatedAdmin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> adminDtos = new ArrayList<>(adminService.getAllAdmin());
        return ResponseEntity.ok(adminDtos);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable int id) {
        return adminService.getAdminById(id);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> removeAdmin(@PathVariable int id) {
        adminService.removeAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
