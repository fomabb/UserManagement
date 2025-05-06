package com.example.testwork.mapper;

import com.example.testwork.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionMapper extends JpaRepository<Subscription, Long> {
}
