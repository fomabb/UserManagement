package com.example.testwork.controller;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionTopResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<List<SubscriptionUserDataResponse>> getSubscriptionByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByUserId(userId));
    }

    @DeleteMapping("/{sub_id}/users/{id}")
    public ResponseEntity<Void> deleteSubscriptionByIdsUserSubscription(
            @PathVariable("sub_id") Long subId,
            @PathVariable("id") Long userId
    ) {
        subscriptionService.deleteSubscriptionByIdsUserSubscription(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    public List<SubscriptionTopResponse> getTop3PopularSubscriptions() {
        return subscriptionService.getTop3PopularSubscriptions();
    }
}
