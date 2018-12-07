package com.example.restdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {
    private UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/users")
    public List<User>users(){
        return userRepository.findAll();
    }
    @PostMapping("/api/users")
    public User insert(@RequestBody User user){

        return userRepository.save(user);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){ ;
            User user1 = userOptional.get();
            user1.setName(user.getName());
            user1.setAge(user.getAge());
            userRepository.save(user1);
            return ResponseEntity.ok(user1);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
