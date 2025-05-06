package com.example.testwork.dto.request;

import com.example.testwork.entity.enumerate.SubscriptionType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionDataCreateRequest {

    private SubscriptionType subscriptionName;
}
