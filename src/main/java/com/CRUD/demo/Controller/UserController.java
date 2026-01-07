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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userDB.put(user.getId(), user);
        System.out.println("User Created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.put(id, user);
            System.out.println("update successful");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            System.out.println("no such user found on user id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.remove(id);
            System.out.println("User deleted successfully");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            System.out.println("no such user found on user id: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}