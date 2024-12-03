package ibrahim.car.management.service;

import ibrahim.car.management.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {

    List<Client> see();
    Client login(Client client);
    void delete(Long id);
    Client purchase(Long id);
    Optional<Client> getClientById(Long id);
}
