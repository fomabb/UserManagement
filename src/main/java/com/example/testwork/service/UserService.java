package com.example.testwork.service;

import com.example.testwork.dto.UserDataDto;
import com.example.testwork.dto.request.UserDataRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;

public interface UserService {

    UserDataCreateResponse createUser(UserDataRequest dto);

    UserDataDto getUserById(Long id);

    void updateUser(Long id);

    void deleteUser(Long id);
}
