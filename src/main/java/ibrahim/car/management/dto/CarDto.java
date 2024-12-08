package ibrahim.car.management.dto;

import ibrahim.car.management.model.Car;

public record CarDto(
        Integer id,
        String name,
        String model,
        String color,
        ClientDto client,
        Double price,
        Boolean available
) {
    public static CarDto fromEntity(Car car) {
        return new CarDto(
                car.getId(),
                car.getName(),
                car.getModel(),
                car.getColor(),
                ClientDto.fromEntity(car.getClient()), // Convert Client entity to ClientDto
                car.getPrice(),
                car.getAvailable()
        );
    }

    public Car toEntity() {
        return Car.builder()
                .id(id)
                .name(name)
                .model(model)
                .color(color)
                .client(client != null ? client.toEntity() : null) // Convert ClientDto back to Client, check if client is not null
                .price(price)
                .available(available)
                .build();
    }
}
