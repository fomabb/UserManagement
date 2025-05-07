package com.example.testwork.controller;

import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDataCreateRequest request = new UserDataCreateRequest();
        UserDataCreateResponse response = new UserDataCreateResponse();

        when(userService.createUser(any(UserDataCreateRequest.class))).thenReturn(response);

        ResponseEntity<UserDataCreateResponse> result = userController.createUser(request);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(userService).createUser(request);
    }

    @Test
    public void testGetUserById_UserFound() {
        Long userId = 1L;
        UserDataInfoResponse response = new UserDataInfoResponse();

        when(userService.getUserById(userId)).thenReturn(response);

        ResponseEntity<UserDataInfoResponse> result = userController.getUserById(userId);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(userService).getUserById(userId);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> result = userController.deleteUserById(userId);

        assertEquals(204, result.getStatusCodeValue());
        verify(userService).deleteUser(userId);
    }
}
