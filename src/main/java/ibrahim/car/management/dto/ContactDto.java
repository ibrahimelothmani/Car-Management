package ibrahim.car.management.dto;

import ibrahim.car.management.model.Contact;

public record ContactDto(
        Integer id,
        String name,
        String email,
        String message
) {
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
