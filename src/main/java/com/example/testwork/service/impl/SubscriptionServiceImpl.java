package com.example.testwork.service.impl;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionTopResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.SubscriptionStats;
import com.example.testwork.entity.User;
import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.entity.enumerate.SubscriptionType;
import com.example.testwork.exceptionhandler.exception.BusinessException;
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

import static com.example.testwork.util.constant.ConstantProject.SUBSCRIPTION_DOES_NOT_BELONG_TO_USER_WITH_ID;
import static com.example.testwork.util.constant.ConstantProject.SUBSCRIPTION_WITH_ID_NOT_FOUND;
import static com.example.testwork.util.constant.ConstantProject.USER_ALREADY_HAS_THIS_SUBSCRIPTION;
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
        log.info("Добавление подписки для пользователя с ID: {}", userId);

        User user = getUserById(userId);
        log.info("Пользователь найден: {}", user);

        SubscriptionTermination subscriptionTermination = dto.getSubscriptionTermination();
        SubscriptionType type = dto.getSubscriptionName();

        Subscription existingSubscription = subscriptionRepository.findByUserIdAndType(userId, type);
        if (existingSubscription != null) {
            log.error("Пользователь с ID: {} уже имеет подписку типа: {}", userId, type);
            throw new BusinessException(USER_ALREADY_HAS_THIS_SUBSCRIPTION);
        }

        SubscriptionStats stats = subscriptionStatsRepository.findById(type)
                .orElseGet(() -> {
                    log.info("Создание новой статистики для подписки типа: {}", type);
                    return new SubscriptionStats(type, 0);
                });

        stats.setPopularity(stats.getPopularity() + 1);
        subscriptionStatsRepository.save(stats);
        log.info("Популярность подписки типа {} увеличена до {}", type, stats.getPopularity());

        Subscription subscription = subscriptionMapper.createSubscriptionRequestToEntity(subscriptionTermination, user, dto);
        subscription = subscriptionRepository.save(subscription);
        log.info("Подписка добавлена: {}", subscription);

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
        log.info("Удаление подписки с ID: {} для пользователя с ID: {}", subId, userId);

        Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> {
                    log.error("Подписка с ID: {} не найдена", subId);
                    return new EntityNotFoundException(SUBSCRIPTION_WITH_ID_NOT_FOUND.formatted(subId));
                });

        if (!subscription.getUser().getId().equals(userId)) {
            log.error("Подписка с ID: {} не принадлежит пользователю с ID: {}", subId, userId);
            throw new BusinessException(SUBSCRIPTION_DOES_NOT_BELONG_TO_USER_WITH_ID.formatted(userId));
        }

        subscriptionRepository.delete(subscription);
        log.info("Подписка с ID: {} успешно удалена", subId);

        SubscriptionStats stats = subscriptionStatsRepository.findById(subscription.getType())
                .orElse(null);

        if (stats != null && stats.getPopularity() > 0) {
            stats.setPopularity(stats.getPopularity() - 1);
            subscriptionStatsRepository.save(stats);
            log.info("Популярность подписки типа {} уменьшена до {}", subscription.getType(), stats.getPopularity());
        }
    }

    @Override
    public List<SubscriptionTopResponse> getTop3PopularSubscriptions() {
        return subscriptionStatsRepository.findTop3ByOrderByPopularityDesc().stream()
                .map(stat -> SubscriptionTopResponse.builder()
                        .name(stat.getType())
                        .popularity(stat.getPopularity())
                        .build())
                .toList();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(userId))
        );
    }
}
