package ibrahim.car.management.controller;

import ibrahim.car.management.dto.PurchaseDto;
import ibrahim.car.management.model.PurchaseRq;
import ibrahim.car.management.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin("*")

public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDto> getPurchaseById(@PathVariable int id) {
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping("/purchases")
    public ResponseEntity<List<PurchaseDto>> getAllPurchases() {
        List<PurchaseDto> purchaseDtos = purchaseService.getAllPurchases().stream()
                .collect(Collectors.toList());
        return ResponseEntity.ok(purchaseDtos);
    }

    @PostMapping("/add")
    public ResponseEntity<PurchaseDto> addPurchase(@RequestBody PurchaseDto purchaseDto) {
        PurchaseDto savedPurchase = purchaseService.addPurchase(purchaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPurchase);
    }

    @PostMapping("/buy")
    public ResponseEntity<PurchaseDto> createPurchase(@RequestBody PurchaseRq purchaseRq) {
        PurchaseDto purchaseDto = PurchaseDto.builder()
                .clientId(purchaseRq.getIdClient())
                .carId(purchaseRq.getIdCar())
                .card(purchaseRq.getCard())
                .build();

        PurchaseDto savedPurchase = purchaseService.addPurchase(purchaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPurchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDto> updatePurchase(@PathVariable("id") int id, @RequestBody PurchaseDto purchaseDto) {
        PurchaseDto updatedPurchase = purchaseService.updatePurchase(id, purchaseDto);
        if (updatedPurchase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedPurchase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePurchase(@PathVariable int id) {
        purchaseService.removePurchase(id);
        return ResponseEntity.noContent().build();
    }
}
