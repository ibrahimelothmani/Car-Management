package ibrahim.car.management.service;

import ibrahim.car.management.dto.AdminDto;
import ibrahim.car.management.model.Admin;
import ibrahim.car.management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<AdminDto> getAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(value -> ResponseEntity.ok(AdminDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public List<AdminDto> getAllAdmin() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(AdminDto::fromEntity).collect(Collectors.toList());
    }

    public AdminDto addAdmin(AdminDto adminDto) {
        Admin admin = adminDto.toEntity();
        Admin savedAdmin = adminRepository.save(admin);
        return AdminDto.fromEntity(savedAdmin);
    }

    public AdminDto updateAdmin(int id, AdminDto adminDto) {
        Admin admin = adminDto.toEntity();
        admin.setId(id);
        Admin updatedAdmin = adminRepository.save(admin);
        return AdminDto.fromEntity(updatedAdmin);
    }

    public void removeAdmin(int id) {
        adminRepository.deleteById(id);
    }

    public ResponseEntity<Map<String, Object>> loginAdmin(AdminDto adminDto) {
        Optional<Admin> admin = adminRepository.findByUsernameAndPassword(adminDto.getUsername(), adminDto.getPassword());
        if (admin.isPresent()) {
            return ResponseEntity.ok(Map.of("message", "login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "invalid credentials"));
        }
    }

    public boolean existsByEmail(String email) {
        return adminRepository.findByEmail(email).isPresent();
    }
}
