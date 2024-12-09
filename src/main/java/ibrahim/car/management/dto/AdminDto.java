package ibrahim.car.management.dto;

import ibrahim.car.management.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminDto {
    private Integer id;
    private String username;
    private String password;
    private String email;


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
