package com.CRUD.demo.Service;

import com.CRUD.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<Integer, User> userDB = new HashMap<>();

    public boolean createUser(User user) {
        return userDB.putIfAbsent(user.getId(), user) == null;
    }

    public User updateUser(User user) {
        if (!userDB.containsKey(user.getId()))
            return null;
        userDB.put(user.getId(),user);
        return user;
    }

    public boolean deleteUser(int id) {
        if (userDB.containsKey(id)) {
            userDB.remove(id);
            return true;
        } else
            return false;
    }

    public User getUserById(int id) {
        return userDB.getOrDefault(id, null);
    }

    public List<User> getAllUser() {
        return new ArrayList<>(userDB.values());
    }
}
