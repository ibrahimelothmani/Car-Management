package ibrahim.car.management.repository;

import ibrahim.car.management.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository {
    List<Purchase> findAll();
}
