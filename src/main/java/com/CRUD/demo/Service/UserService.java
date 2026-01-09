package com.CRUD.demo.Service;

import com.CRUD.demo.Exceptions.UserNotFoundException;
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

    public User updateUser(int id , User user) {
        if (!userDB.containsKey(id))
            throw new UserNotFoundException("User not found with id: " + id);
        userDB.put(user.getId(),user);
        return user;
    }

    public String  deleteUser(int id) {
        if (!userDB.containsKey(id))
            throw new UserNotFoundException("User not found with id: " + id);
        userDB.remove(id);
        return "USer deleted Successfully";
    }

    public User getUserById(int id) {
        User user = userDB.get(id);

        if (user == null)
            throw new UserNotFoundException("User not found with id: " + id);

        return user;
    }

    public List<User> getAllUser() {
        return new ArrayList<>(userDB.values());
    }
}
