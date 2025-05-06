package com.example.testwork.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SubscriptionUserDataResponse {

    private Long userId;

    private Long subscriptionId;

    private String name;

    private String description;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
