package PochingLao.Controller;

import PochingLao.entity.User;
import PochingLao.Repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUser() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
