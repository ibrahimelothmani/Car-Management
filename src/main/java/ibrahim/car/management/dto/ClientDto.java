package ibrahim.car.management.dto;

import ibrahim.car.management.model.Car;
import ibrahim.car.management.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public record ClientDto(
        Integer id,
        String name,
        String password,
        Integer phone,
        String email,
        String address,
        List<CarDto> cars // Changed to List<CarDto> to handle multiple cars
) {
    public static ClientDto fromEntity(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getPassword(),
                client.getPhone(),
                client.getEmail(),
                client.getAddress(),
                client.getCars().stream()
                        .map(CarDto::fromEntity)
                        .collect(Collectors.toList()) // Collect all cars into a list
        );
    }

    public Client toEntity() { // Removed the argument to avoid confusion
        return Client.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .email(this.email)
                .address(this.address)
                .cars(this.cars.stream()
                        .map(CarDto::toEntity) // Convert each CarDto to Car
                        .collect(Collectors.toList())) // Collect into a list of Car
                .build();
    }
}
