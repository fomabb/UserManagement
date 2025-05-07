package com.example.testwork.repository;

import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.enumerate.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserIdAndType(Long userId, SubscriptionType subscriptionName);

    List<Subscription> findSubscriptionsByUserId(Long userId);
}
