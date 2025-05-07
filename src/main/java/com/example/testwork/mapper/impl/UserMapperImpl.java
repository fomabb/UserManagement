package com.example.testwork.mapper.impl;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;
import com.example.testwork.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;

@Component
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDataCreateResponse userEntityToCreateResponse(User user) {
        return UserDataCreateResponse.builder()
                .userId(user.getId())
                .build();
    }

    @Override
    public User createRequestToEntity(UserDataCreateRequest dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .createAt(now())
                .build();
    }

    @Override
    public UserDataInfoResponse userEntityToUserInfoResponse(User user) {
        return UserDataInfoResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    @Override
    public User updateUserDtoToUpdateUserEntity(UpdateUserRequest dto) {
        return User.builder()
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .updateAt(now())
                .build();
    }

    @Override
    public User buildUpdateUserForSave(User existingUser, User updatedUser) {
        return User.builder()
                .id(existingUser.getId())
                .firstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : existingUser.getFirstName())
                .lastName(updatedUser.getLastName() != null ? updatedUser.getLastName() : existingUser.getLastName())
                .email(updatedUser.getEmail() != null ? updatedUser.getEmail() : existingUser.getEmail())
                .createAt(existingUser.getCreateAt())
                .updateAt(now())
                .build();
    }
}
