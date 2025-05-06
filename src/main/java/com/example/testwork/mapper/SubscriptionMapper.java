package com.example.testwork.mapper;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.User;

public interface SubscriptionMapper {

    SubscriptionDataCreateResponse subscriptionEntityToCreateSubscriptionResponse(User user, Subscription subscription);

    Subscription createSubscriptionRequestToEntity(User user, SubscriptionDataCreateRequest dto);
}
