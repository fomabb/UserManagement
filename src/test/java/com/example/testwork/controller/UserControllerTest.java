package com.example.testwork.controller;

import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.exceptionhandler.exception.BusinessException;
import com.example.testwork.facade.UserFacade;
import com.example.testwork.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private UserFacade userFacade;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ShouldReturnUserDataCreateResponse_WhenPasswordsMatch() {
        UserDataCreateRequest request = new UserDataCreateRequest();
        request.setPassword("password123");
        request.setConfirmPassword("password123");

        UserDataCreateResponse response = new UserDataCreateResponse();

        when(userService.createUser(any(UserDataCreateRequest.class))).thenReturn(response);

        UserDataCreateResponse result = userFacade.createUser(request);

        assertEquals(response, result);
        verify(userService).createUser(request);
    }

    @Test
    void createUser_ShouldThrowBusinessException_WhenPasswordsDoNotMatch() {
        UserDataCreateRequest request = new UserDataCreateRequest();
        request.setPassword("password123");
        request.setConfirmPassword("differentPassword");

        assertThrows(BusinessException.class, () -> userFacade.createUser(request));
        verify(userService, never()).createUser(any());
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
