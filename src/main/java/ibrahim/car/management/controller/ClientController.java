package ibrahim.car.management.controller;

import ibrahim.car.management.dto.ClientDto;
import ibrahim.car.management.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginClient(@RequestBody ClientDto clientDto) {
        return clientService.loginClient(clientDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addClient(@RequestBody ClientDto clientDto) {
        if (clientService.existsByEmail(clientDto.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Email already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        clientDto.setPassword(bCryptPasswordEncoder.encode(clientDto.getPassword()));
        ClientDto savedClient = clientService.addClient(clientDto);
        Map<String, Object> response = new HashMap<>();
        response.put("client", savedClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") int id, @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.updateClient(id, clientDto);
        if (updatedClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedClient);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientDtos = new ArrayList<>(clientService.getAllClients());
        return ResponseEntity.ok(clientDtos);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> removeClient(@PathVariable int id) {
        clientService.removeClient(id);
        return ResponseEntity.noContent().build();
    }
}
