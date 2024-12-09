package ibrahim.car.management.service;


import ibrahim.car.management.model.Contact;
import ibrahim.car.management.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }
}
