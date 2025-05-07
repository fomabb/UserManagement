package com.example.testwork.entity.enumerate;

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
}
