package com.example.testwork.service;

import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;

public interface SubscriptionService {

    SubscriptionDataCreateResponse addSubscriptionByUserById(Long userId);

    SubscriptionUserDataResponse getSubscriptionByUserId(Long userId);

    void deleteSubscriptionByIdsUserSubscription(Long userId, Long subId);
}
