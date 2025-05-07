package com.example.testwork.service.impl;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.SubscriptionStats;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.entity.enumerate.SubscriptionType;
import com.example.testwork.mapper.SubscriptionMapper;
import com.example.testwork.repository.SubscriptionRepository;
import com.example.testwork.repository.SubscriptionStatsRepository;
import com.example.testwork.repository.UserRepository;
import com.example.testwork.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.testwork.util.constant.ConstantProject.USER_WITH_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionStatsRepository subscriptionStatsRepository;

    @Override
    @Transactional
    public SubscriptionDataCreateResponse addSubscriptionByUserById(Long userId, SubscriptionDataCreateRequest dto) {
        User user = getUserById(userId);

        SubscriptionTermination subscriptionTermination = dto.getSubscriptionTermination();
        SubscriptionType type = dto.getSubscriptionName();

        Subscription existingSubscription = subscriptionRepository.findByUserIdAndType(userId, type);
        if (existingSubscription != null) {
            throw new EntityNotFoundException("User already has this subscription");
        }

        SubscriptionStats stats = subscriptionStatsRepository.findById(type)
                .orElseGet(() -> new SubscriptionStats(type, 0));

        stats.setPopularity(stats.getPopularity() + 1);
        subscriptionStatsRepository.save(stats);

        Subscription subscription = subscriptionMapper.createSubscriptionRequestToEntity(subscriptionTermination, user, dto);
        subscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.subscriptionEntityToCreateSubscriptionResponse(user, subscription);
    }

    @Override
    public List<SubscriptionUserDataResponse> getSubscriptionByUserId(Long userId) {
        User user = getUserById(userId);
        return subscriptionMapper.subscriptionListEntityToSubscriptionListDto(subscriptionRepository.findSubscriptionsByUserId(user.getId()));
    }

    @Override
    @Transactional
    public void deleteSubscriptionByIdsUserSubscription(Long userId, Long subId) {
        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with ID %d not found".formatted(subId)));

        if (!subscription.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Subscription does not belong to user with ID " + userId);
        }

        subscriptionRepository.delete(subscription);

        SubscriptionStats stats = subscriptionStatsRepository.findById(subscription.getType())
                .orElse(null);

        if (stats != null && stats.getPopularity() > 0) {
            stats.setPopularity(stats.getPopularity() - 1);
            subscriptionStatsRepository.save(stats);
        }
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(userId))
        );
    }
}


