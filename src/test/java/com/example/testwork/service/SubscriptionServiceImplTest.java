package com.example.testwork.service;

import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.User;
import com.example.testwork.exceptionhandler.exception.BusinessException;
import com.example.testwork.repository.SubscriptionRepository;
import com.example.testwork.service.impl.SubscriptionServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SubscriptionServiceImplTest {

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteSubscription_UserNotFound() {
        Long userId = 1L;
        Long subId = 2L;

        when(subscriptionRepository.findById(subId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> subscriptionService.deleteSubscriptionByIdsUserSubscription(userId, subId));
    }

    @Test
    public void testDeleteSubscription_SubscriptionDoesNotBelongToUser() {
        Long userId = 1L;
        Long subId = 2L;
        Subscription subscription = new Subscription();
        subscription.setUser(new User());
        subscription.getUser().setId(3L); // другой пользователь

        when(subscriptionRepository.findById(subId)).thenReturn(Optional.of(subscription));

        assertThrows(BusinessException.class, () -> subscriptionService.deleteSubscriptionByIdsUserSubscription(userId, subId));
    }
}
