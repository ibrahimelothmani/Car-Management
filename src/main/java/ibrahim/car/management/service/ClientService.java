package ibrahim.car.management.service;

import ibrahim.car.management.dto.ClientDto;
import ibrahim.car.management.model.Client;
import ibrahim.car.management.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<ClientDto> getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> ResponseEntity.ok(ClientDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientDto.toEntity();
        Client savedClient = clientRepository.save(client);
        return ClientDto.fromEntity(savedClient);
    }

    public ClientDto updateClient(int id, ClientDto clientDto) {
        Client client = clientDto.toEntity();
        client.setId(id);
        Client updatedClient = clientRepository.save(client);
        return ClientDto.fromEntity(updatedClient);
    }

    public void removeClient(int id) {
        clientRepository.deleteById(id);
    }

    public ResponseEntity<Map<String, Object>> loginClient(ClientDto clientDto) {
        Optional<Client> client = clientRepository.findByEmail(clientDto.getEmail());
        if (client.isPresent() && client.get().getPassword().equals(clientDto.getPassword())) {
            return ResponseEntity.ok(Map.of("message", "login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "invalid credentials"));
        }
    }

    public boolean existsByEmail(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }
}
