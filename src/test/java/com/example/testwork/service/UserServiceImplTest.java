package com.example.testwork.service;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;
import com.example.testwork.mapper.UserMapper;
import com.example.testwork.repository.UserRepository;
import com.example.testwork.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDataCreateRequest request = new UserDataCreateRequest();
        User user = new User();
        UserDataCreateResponse response = new UserDataCreateResponse();

        when(userMapper.createRequestToEntity(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userEntityToCreateResponse(user)).thenReturn(response);

        UserDataCreateResponse result = userService.createUser(request);

        verify(userRepository).save(user);
        assertNotNull(result);
    }

    @Test
    public void testGetUserById_UserFound() {
        Long userId = 1L;
        User user = new User();
        UserDataInfoResponse response = new UserDataInfoResponse();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.userEntityToUserInfoResponse(user)).thenReturn(response);

        UserDataInfoResponse result = userService.getUserById(userId);

        assertNotNull(result);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        UpdateUserRequest updateRequest = new UpdateUserRequest();
        User existingUser = new User();
        User updatedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userMapper.updateUserDtoToUpdateUserEntity(updateRequest)).thenReturn(updatedUser);
        when(userMapper.buildUpdateUserForSave(existingUser, updatedUser)).thenReturn(updatedUser);

        userService.updateUser(userId, updateRequest);

        verify(userRepository).save(updatedUser);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        UpdateUserRequest updateRequest = new UpdateUserRequest();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(userId, updateRequest));
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        User user = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}