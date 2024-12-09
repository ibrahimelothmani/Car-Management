package ibrahim.car.management.repository;


import ibrahim.car.management.dto.ClientDto;
import ibrahim.car.management.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository {
    List<ClientDto> findAll();

    Client save(Client client);

    void deleteById(int id);

    Optional<Client> findById(int id);

    Client findByEmail(String email);
}
