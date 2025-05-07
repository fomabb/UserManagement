package com.example.testwork.service;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionTopResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDataCreateResponse addSubscriptionByUserById(Long userId, SubscriptionDataCreateRequest dto);

    List<SubscriptionUserDataResponse> getSubscriptionByUserId(Long userId);

    void deleteSubscriptionByIdsUserSubscription(Long userId, Long subId);

    List<SubscriptionTopResponse> getTop3PopularSubscriptions();
}
