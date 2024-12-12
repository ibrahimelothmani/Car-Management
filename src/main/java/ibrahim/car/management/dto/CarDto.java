package ibrahim.car.management.dto;

import ibrahim.car.management.model.Car;
import ibrahim.car.management.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Integer id;
    private String name;
    private String model;
    private String color;
    private Double price;
    private Boolean available;
    private Integer clientId;

    public static CarDto fromEntity(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .model(car.getModel())
                .color(car.getColor())
                .price(car.getPrice())
                .available(car.getAvailable())
                .clientId(car.getClient() != null ? car.getClient().getId() : null)
                .build();
    }

    public Car toEntity() {
        return Car.builder()
                .id(this.id)
                .name(this.name)
                .model(this.model)
                .color(this.color)
                .price(this.price)
                .available(this.available)
                .client(this.clientId != null ? Client.builder().id(this.clientId).build() : null)
                .build();
    }
}
