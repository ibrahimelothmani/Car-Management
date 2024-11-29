package ibrahim.car.management.model;

import java.time.LocalDateTime;

public record User(
        Integer id,
        String name,
        String email,
        String password,
        int phone,
        LocalDateTime dateTime,
        Type type
) {}
