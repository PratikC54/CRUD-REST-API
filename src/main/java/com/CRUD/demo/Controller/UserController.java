package com.CRUD.demo.Controller;

import com.CRUD.demo.Service.UserService;
import com.CRUD.demo.dto.UserRequestDTO;
import com.CRUD.demo.dto.UserResponseDTO;
import com.CRUD.demo.entity.User;
import com.CRUD.demo.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUser() {
        List<UserResponseDTO> users =  userService.getAllUser()
                .stream()
                .map(UserMapper::toResponse).toList();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        User user = userService.createUser(UserMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toResponse(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO dto , @PathVariable int id) {
        User user = userService.updateUser(id, UserMapper.toEntity(dto));
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}