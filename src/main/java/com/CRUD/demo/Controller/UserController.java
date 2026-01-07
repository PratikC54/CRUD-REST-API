package com.CRUD.demo.Controller;

import com.CRUD.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer, User> userDB = new HashMap<>();

    @GetMapping("/get")
    public List<User> getUser() {
        return new ArrayList<>(userDB.values());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userDB.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user , @PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.put(id, user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("update successful");
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such user found on user id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.remove(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        } else
            return new ResponseEntity<>("no such user found on user id: " + id , HttpStatus.NOT_FOUND);
    }
}