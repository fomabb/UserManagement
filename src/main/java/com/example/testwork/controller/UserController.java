package com.example.testwork.controller;

import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.dto.request.UserDataRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Управление пользователями", description = "`Интерфейс для управления пользователями`")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDataCreateResponse> createUser(@RequestBody @Valid UserDataRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataInfoResponse> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
