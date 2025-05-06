package com.example.testwork.controller;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Управление подписками", description = "`Интерфейс для управления подписками пользователя`")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}")
    public ResponseEntity<SubscriptionDataCreateResponse> createSubscription(
            @PathVariable("id") Long userId,
            @RequestBody SubscriptionDataCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.addSubscriptionByUserById(userId, request));
    }
}
