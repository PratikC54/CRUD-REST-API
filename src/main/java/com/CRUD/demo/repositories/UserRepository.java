package com.CRUD.demo.repositories;

import com.CRUD.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<Integer, User> userDB = new HashMap<>();

    public boolean userExist(int id) {
        return userDB.containsKey(id);
    }

    public User saveUser(User user) {
        userDB.put(user.getId(), user);
        return user;
    }

    public User findUserById(int id) {
        return userDB.get(id);
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(userDB.values());
    }

    public void deleteUser(int id) {
        userDB.remove(id);
    }
}
