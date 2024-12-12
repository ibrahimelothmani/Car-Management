package ibrahim.car.management.dto;

import ibrahim.car.management.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContactDto {
        private Integer id;
        private String name;
        private String email;
        private String message;
    public static ContactDto fromEntity(Contact contact){
        return new ContactDto(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMessage()
        );
    }
    public Contact toEntity(){
        return Contact.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .message(this.message)
                .build();
    }

}
