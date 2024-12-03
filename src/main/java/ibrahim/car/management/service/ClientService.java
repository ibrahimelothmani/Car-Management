package ibrahim.car.management.service;

import ibrahim.car.management.model.Client;

import java.util.List;
import java.util.Optional;

public class ClientService implements ClientServiceInterface{
    @Override
    public List<Client> see() {
        return List.of();
    }

    @Override
    public Client login(Client client) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Client purchase(Long id) {
        return null;
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return Optional.empty();
    }
}
