package ibrahim.car.management.dto;

import ibrahim.car.management.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Integer id;
    private String name;
    private String password;
    private Integer phone;
    private String email;
    private String address;
    private List<CarDto> cars;

    public static ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .password(client.getPassword())
                .phone(client.getPhone())
                .email(client.getEmail())
                .address(client.getAddress())
                .cars(client.getCars().stream().map(CarDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public Client toEntity() {
        return Client.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .email(this.email)
                .address(this.address)
                .cars(this.cars.stream().map(CarDto::toEntity).collect(Collectors.toList()))
                .build();
    }
}
