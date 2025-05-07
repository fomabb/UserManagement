package com.example.testwork.dto.response;

import com.example.testwork.entity.enumerate.SubscriptionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionTopResponse {
    private SubscriptionType name;
    private int popularity;
}
