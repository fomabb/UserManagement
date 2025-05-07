package com.example.testwork.repository;

import com.example.testwork.entity.Subscription;
import com.example.testwork.entity.enumerate.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserIdAndType(Long userId, SubscriptionType subscriptionName);

    Subscription findSubscriptionByDescription(String description);
}
