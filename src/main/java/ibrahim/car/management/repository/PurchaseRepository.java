package ibrahim.car.management.repository;

import ibrahim.car.management.dto.PurchaseDto;
import ibrahim.car.management.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository {
    List<PurchaseDto> findAll();
    List<Purchase> findByClientId(Long id);
    Optional<Object> findById(Long id);
    boolean existsByClientIdAndCarId(Integer idClient, Integer idCar);
    Purchase save(Purchase purchase);
}
