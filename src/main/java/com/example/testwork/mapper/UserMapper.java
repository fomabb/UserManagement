package com.example.testwork.mapper;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;

public interface UserMapper {

    UserDataCreateResponse userEntityToCreateResponse(User user);

    User createRequestToEntity(UserDataCreateRequest dto);

    UserDataInfoResponse userEntityToUserInfoResponse(User user);

    User updateUserDtoToUpdateUserEntity(UpdateUserRequest dto);

    User buildUpdateUserForSave(User existingUser, User updatedUser);
}
