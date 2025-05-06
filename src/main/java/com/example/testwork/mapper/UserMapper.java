package com.example.testwork.mapper;

import com.example.testwork.dto.request.UserDataRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;

import java.util.Optional;

public interface UserMapper {

    UserDataCreateResponse userEntityToCreateResponse(User user);

    User createRequestToEntity(UserDataRequest dto);

    UserDataInfoResponse userEntityToUserInfoResponse(User user);
}
