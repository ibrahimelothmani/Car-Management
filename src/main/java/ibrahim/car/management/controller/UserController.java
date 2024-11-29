package ibrahim.car.management.controller;


import ibrahim.car.management.model.User;
import ibrahim.car.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Implement CRUD operations here

    @GetMapping("/get")
    public List<User> findAll(){
        return repository.findAll();
    }
}
