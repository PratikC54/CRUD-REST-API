package com.CRUD.demo.service;

import com.CRUD.demo.Service.UserService;
import com.CRUD.demo.entity.User;
import com.CRUD.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser);
        verify(userRepository).save(user);
    }
}
