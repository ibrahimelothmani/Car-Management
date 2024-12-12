package ibrahim.car.management.repository;

import ibrahim.car.management.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsernameAndPassword(String username, String password);
    Optional<Admin> findByEmail(String email);
}
