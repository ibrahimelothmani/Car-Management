package ibrahim.car.management.repository;

import ibrahim.car.management.dto.AdminDto;
import ibrahim.car.management.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository {

    void delete(int admin);
    Optional<Object> findById(int id);
    AdminDto save(AdminDto admin);
    List<AdminDto> findAll();
    boolean existsByEmail(String email);
    Admin findAdminByEmail(String email);
}
