package com.example.testwork.entity.enumerate;

import com.example.testwork.entity.SubscriptionStats;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public enum SubscriptionTermination {

    MONTHLY_SUBSCRIPTION,
    SUBSCRIPTION_TO_THE_QUARTER,
    SUBSCRIPTION_FOR_A_YEAR;

    public LocalDateTime getSubscriptionTermination() {
        return switch (this) {
            case MONTHLY_SUBSCRIPTION -> LocalDateTime.now().plusMonths(1);
            case SUBSCRIPTION_TO_THE_QUARTER -> LocalDateTime.now().plusMonths(3);
            case SUBSCRIPTION_FOR_A_YEAR -> LocalDateTime.now().plusYears(1);
        };
    }

    public BigDecimal getPrice(SubscriptionStats stats) {
        return switch (this) {
            case MONTHLY_SUBSCRIPTION -> stats.getPrice();
            case SUBSCRIPTION_TO_THE_QUARTER ->
                    stats.getPrice().multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.9)); // 10% скидка
            case SUBSCRIPTION_FOR_A_YEAR ->
                    stats.getPrice().multiply(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(0.8)); // 20% скидка
        };
    }
}
