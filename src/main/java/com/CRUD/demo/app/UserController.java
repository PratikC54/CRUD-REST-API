package com.CRUD.demo.app;

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
    public String createUser(@RequestBody User user) {
        userDB.put(user.getId(), user);
        return "User created";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody User user , @PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.put(id, user);
            return "update sucessful";
        } else
            return "no such user found on user id: "+id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        if (userDB.containsKey(id)) {
            userDB.remove(id);
            return "User Deleted Sucessfully";
        } else
            return "no such user found on user id: "+id;
    }
}