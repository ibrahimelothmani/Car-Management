package ibrahim.car.management.dto;

import ibrahim.car.management.model.Admin;

public record AdminDto(
        Integer id,
        String username,
        String password,
        String email
) {

    public static AdminDto fromEntity(Admin admin) {
        return new AdminDto(
                admin.getId(),
                admin.getUsername(),
                admin.getPassword(),
                admin.getEmail()
        );
    }
    public Admin toEntity(){
        return Admin.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
