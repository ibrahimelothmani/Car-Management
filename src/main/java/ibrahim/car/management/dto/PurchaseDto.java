package ibrahim.car.management.dto;

import ibrahim.car.management.model.Car;
import ibrahim.car.management.model.Client;
import ibrahim.car.management.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
    private Integer id;
    private Integer clientId;
    private Integer carId;
    private String card;

    public static PurchaseDto fromEntity(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .clientId(purchase.getClient() != null ? purchase.getClient().getId() : null)
                .carId(purchase.getCar() != null ? purchase.getCar().getId() : null)
                .card(purchase.getCard())
                .build();
    }

    public Purchase toEntity() {
        return Purchase.builder()
                .id(this.id)
                .client(this.clientId != null ? Client.builder().id(this.clientId).build() : null)
                .car(this.carId != null ? Car.builder().id(this.carId).build() : null)
                .card(this.card)
                .build();
    }
}
