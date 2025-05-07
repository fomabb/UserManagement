package com.example.testwork.mapper.impl;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.mapper.SubscriptionMapper;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;

@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {
    @Override
    public SubscriptionDataCreateResponse subscriptionEntityToCreateSubscriptionResponse(User user, Subscription subscription) {
        return SubscriptionDataCreateResponse.builder()
                .userId(user.getId())
                .subscriptionId(subscription.getId())
                .build();
    }

    @Override
    public Subscription createSubscriptionRequestToEntity(
            Subscription existingSubscription,
            SubscriptionTermination subscriptionTermination,
            User user,
            SubscriptionDataCreateRequest dto
    ) {
        return Subscription.builder()
                .user(User.builder().id(user.getId()).build())
                .type(dto.getSubscriptionName())
                .createAt(now())
                .subscriptionTermination(subscriptionTermination)
                .description(dto.getSubscriptionName().getDescription())
                .price(dto.getSubscriptionName().getPrice(subscriptionTermination))
                .expirationDate(dto.getSubscriptionTermination().getSubscriptionTermination())
                .popularity(existingSubscription != null ? existingSubscription.getPopularity() + 1 : dto.getSubscriptionName().getPopularity())
                .build();
    }
}
