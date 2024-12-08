package ibrahim.car.management.repository;

import ibrahim.car.management.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository {

    void delete(Admin admin);
    Optional<Object> findById(int id);
    Admin save(Admin admin);
    List<Admin> findAll();
    boolean existsByEmail(String email);
    Admin findAdminByEmail(String email);
}
