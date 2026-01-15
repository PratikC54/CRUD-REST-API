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
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("User already exists!!!!");
        return userRepository.save(user);
    }

    public User updateUser(int id , User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("User not found with id: " + id);
        userRepository.deleteById(id);
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id "+id));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
