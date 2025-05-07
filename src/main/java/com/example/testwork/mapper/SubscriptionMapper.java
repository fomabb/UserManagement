package com.example.testwork.mapper;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;

import java.util.List;

public interface SubscriptionMapper {

    SubscriptionDataCreateResponse subscriptionEntityToCreateSubscriptionResponse(User user, Subscription subscription);

    Subscription createSubscriptionRequestToEntity(SubscriptionTermination subscriptionTermination, User user, SubscriptionDataCreateRequest dto);

    List<SubscriptionUserDataResponse> subscriptionListEntityToSubscriptionListDto(List<Subscription> subscriptions);
}
