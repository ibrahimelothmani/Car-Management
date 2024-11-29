package ibrahim.car.management.repository;

import ibrahim.car.management.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository {

    public final List<User> user = new ArrayList<User>();

    public UserRepository() {

    }

    public List<User> findAll(){
        return user;
    }
    public Optional<User> findById(Integer id){
        return user.stream().filter(u -> u.id().equals(id)).findFirst();
    }

//    private void init(){
//        User c = new User(
//                id: 1,
//                name: "ibrahim",
//                email: "ibrahim@email.com",
//                password: "123ABC",
//                phone: 12345678,
//                LocalDateTime.now(),
//                Type.ACHIEVED);
//        user.add(c);
//    }

}

