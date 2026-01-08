package com.CRUD.demo.Controller;

import com.CRUD.demo.Service.UserService;
import com.CRUD.demo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService = new UserService();

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUser() {
        List<User> users =  userService.getAllUser();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User>  getUserById(@PathVariable int id) {
        User foundUser = userService.getUserById(id);
        if (foundUser!=null)
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.createUser(user))
            return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user , @PathVariable int id) {
        User updatedUser = userService.updateUser(user);
        if(updatedUser==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such user found on user id: " + id);
        else
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("update successful");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        else
            return new ResponseEntity<>("no such user found on user id: " + id , HttpStatus.NOT_FOUND);
    }
}