package com.example.testwork.mapper.impl;

import com.example.testwork.dto.request.UserDataRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;
import com.example.testwork.mapper.UserMapper;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDataCreateResponse userEntityToCreateResponse(User user) {
        return UserDataCreateResponse.builder()
                .userId(user.getId())
                .build();
    }

    @Override
    public User createRequestToEntity(UserDataRequest dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
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
}
