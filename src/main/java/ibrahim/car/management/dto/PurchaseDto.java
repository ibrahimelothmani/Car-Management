package ibrahim.car.management.dto;

import ibrahim.car.management.model.Car;
import ibrahim.car.management.model.Client;
import ibrahim.car.management.model.Purchase;

public record PurchaseDto(
        Integer id,
        Client client,
        Car car,
        String card
) {
    public static PurchaseDto fromEntity (Purchase purchase) {
        return new PurchaseDto(
                purchase.getId(),
                purchase.getClient(),
                purchase.getCar(),
                purchase.getCard()
        );
    }
    public Purchase toEntity() {
        return Purchase.builder()
                .id(id)
                .client(client)
                .car(car)
                .card(card)
                .build();
    }

}
