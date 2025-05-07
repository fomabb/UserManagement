package com.example.testwork.repository;

import com.example.testwork.entity.SubscriptionStats;
import com.example.testwork.entity.enumerate.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionStatsRepository extends JpaRepository<SubscriptionStats, SubscriptionType> {
}
