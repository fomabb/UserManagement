package com.example.testwork.service;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;

public interface UserService {

    UserDataCreateResponse createUser(UserDataCreateRequest dto);

    UserDataInfoResponse getUserById(Long id);

    void updateUser(Long id, UpdateUserRequest dto);

    void deleteUser(Long id);
}
