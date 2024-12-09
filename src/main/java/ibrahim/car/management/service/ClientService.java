package ibrahim.car.management.service;

import ibrahim.car.management.model.Client;
import ibrahim.car.management.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService{

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client login(Client client) {
        Client existingClient = clientRepository.findByEmail(client.getEmail());
        if (existingClient != null && existingClient.getPassword().equals(client.getPassword())) {
            return existingClient;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
