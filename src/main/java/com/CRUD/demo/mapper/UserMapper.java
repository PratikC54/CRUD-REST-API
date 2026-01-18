package com.CRUD.demo.mapper;

import com.CRUD.demo.dto.UserRequestDTO;
import com.CRUD.demo.dto.UserResponseDTO;
import com.CRUD.demo.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}
