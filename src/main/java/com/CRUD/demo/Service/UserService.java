package com.CRUD.demo.Service;

import com.CRUD.demo.Exceptions.DuplicateUserException;
import com.CRUD.demo.Exceptions.UserNotFoundException;
import com.CRUD.demo.entity.User;
import com.CRUD.demo.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        log.info("Creating user with email={}", user.getEmail());
        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("Duplicate user creation attempt failed for email{}", user.getEmail());
            throw new DuplicateUserException("User already exists!!!!");
        }
        User savedUSer = userRepository.save(user);
        log.info("Uer created successfully with id={}", user.getId());
        return savedUSer;
    }

    public User updateUser(int id, User user) {
        log.info("Updating user with id={}", id);

        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        log.info("User updated successfully with id={}", user.getId());
        return updatedUser;
    }

    public void deleteUser(int id) {
        log.info("Deleting user with id={}", id);
        if (!userRepository.existsById(id)) {
            log.warn("Delete failed. User not found with id={}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted successfully with id={}", id);
    }

    public User getUserById(int id) {
        log.info("Fetching user with id={}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id={}", id);
                    return new UserNotFoundException("User not found with id " + id);
                });
    }

    public Page<User> getUsers(Pageable pageable) {
        log.info("Fetching users with pagination : page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return userRepository.findAll(pageable);
    }
}
