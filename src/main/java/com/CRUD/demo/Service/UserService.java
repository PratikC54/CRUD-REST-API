package com.CRUD.demo.Service;

import com.CRUD.demo.Exceptions.UserNotFoundException;
import com.CRUD.demo.entity.User;
import com.CRUD.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository ;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if(userRepository.userExist(user.getId()))
            throw new RuntimeException("User already exists!!!!");
        return userRepository.saveUser(user);
    }

    public User updateUser(int id , User user) {
        if (!userRepository.userExist(id))
            throw new UserNotFoundException("User not found with id: " + id);
        user.setId(id);
        return userRepository.saveUser(user);
    }

    public void deleteUser(int id) {
        if (!userRepository.userExist(id))
            throw new UserNotFoundException("User not found with id: " + id);
        userRepository.deleteUser(id);
    }

    public User getUserById(int id) {
        User user = userRepository.findUserById(id);

        if (user == null)
            throw new UserNotFoundException("User not found with id: " + id);

        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAllUsers();
    }
}
