package ibrahim.car.management.service;

import ibrahim.car.management.dto.PurchaseDto;
import ibrahim.car.management.model.Purchase;
import ibrahim.car.management.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public ResponseEntity<PurchaseDto> getPurchaseById(int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.map(value -> ResponseEntity.ok(PurchaseDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public List<PurchaseDto> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(PurchaseDto::fromEntity).collect(Collectors.toList());
    }

    public PurchaseDto addPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = purchaseDto.toEntity();
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return PurchaseDto.fromEntity(savedPurchase);
    }

    public PurchaseDto updatePurchase(int id, PurchaseDto purchaseDto) {
        Purchase purchase = purchaseDto.toEntity();
        purchase.setId(id);
        Purchase updatedPurchase = purchaseRepository.save(purchase);
        return PurchaseDto.fromEntity(updatedPurchase);
    }

    public void removePurchase(int id) {
        purchaseRepository.deleteById(id);
    }
}
