package com.example.testwork.mapper.impl;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.SubscriptionStats;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.mapper.SubscriptionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Subscription createSubscriptionRequestToEntity(
            SubscriptionTermination subscriptionTermination,
            User user,
            SubscriptionDataCreateRequest dto,
            SubscriptionStats stats
    ) {
        return Subscription.builder()
                .user(User.builder().id(user.getId()).build())
                .type(dto.getSubscriptionName())
                .createAt(now())
                .subscriptionTermination(subscriptionTermination)
                .description(dto.getSubscriptionName().getDescription())
                .price(subscriptionTermination.getPrice(stats))
                .expirationDate(subscriptionTermination.getSubscriptionTermination())
                .build();
    }

    @Override
    public List<SubscriptionUserDataResponse> subscriptionListEntityToSubscriptionListDto(List<Subscription> subscriptions) {
        return subscriptions.stream().map(subscription -> SubscriptionUserDataResponse.builder()
                        .userId(subscription.getUser().getId())
                        .subscriptionId(subscription.getId())
                        .name(subscription.getType().name())
                        .description(subscription.getDescription())
                        .subscriptionTermination(String.valueOf(subscription.getSubscriptionTermination()))
                        .price(subscription.getPrice())
                        .createAt(subscription.getCreateAt())
                        .expirationDate(subscription.getExpirationDate())
                        .build())
                .toList();
    }
}