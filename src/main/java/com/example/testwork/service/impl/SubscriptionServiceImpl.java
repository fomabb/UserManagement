package com.example.testwork.service.impl;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.mapper.SubscriptionMapper;
import com.example.testwork.repository.SubscriptionRepository;
import com.example.testwork.repository.UserRepository;
import com.example.testwork.service.SubscriptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.testwork.util.constant.ConstantProject.USER_WITH_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @Transactional
    public SubscriptionDataCreateResponse addSubscriptionByUserById(Long userId, SubscriptionDataCreateRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(userId))
        );
        SubscriptionTermination subscriptionTermination = dto.getSubscriptionTermination();

        return subscriptionMapper.subscriptionEntityToCreateSubscriptionResponse(user,
                subscriptionRepository.save(
                        subscriptionMapper.createSubscriptionRequestToEntity(subscriptionTermination, user, dto))
        );
    }

    @Override
    public SubscriptionUserDataResponse getSubscriptionByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteSubscriptionByIdsUserSubscription(Long userId, Long subId) {

    }
}
