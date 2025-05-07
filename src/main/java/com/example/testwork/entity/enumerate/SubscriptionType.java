package com.example.testwork.entity.enumerate;

import java.math.BigDecimal;

public enum SubscriptionType {
    YOUTUBE_PREMIUM,
    VK_MUSIC,
    YANDEX_PLUS,
    NETFLIX,
    DISNEY_PLUS,
    SPOTIFY;

    public String getDescription() {
        return switch (this) {
            case YOUTUBE_PREMIUM -> "YouTube Premium: Без рекламы и доступ к YouTube Music.";
            case VK_MUSIC -> "VK Music: Музыкальный сервис от ВКонтакте.";
            case YANDEX_PLUS -> "Yandex Plus: Подписка на сервисы Яндекса.";
            case NETFLIX -> "Netflix: Стриминговый сервис с фильмами и сериалами.";
            case DISNEY_PLUS -> "Disney Plus: Стриминговый сервис Disney.";
            case SPOTIFY -> "Spotify: Музыкальный стриминговый сервис.";
        };
    }

    public BigDecimal getBasePrice() {
        return switch (this) {
            case YOUTUBE_PREMIUM -> BigDecimal.valueOf(11.99);
            case VK_MUSIC -> BigDecimal.valueOf(4.44);
            case YANDEX_PLUS -> BigDecimal.valueOf(9.01);
            case NETFLIX -> BigDecimal.valueOf(15.11);
            case DISNEY_PLUS -> BigDecimal.valueOf(7.92);
            case SPOTIFY -> BigDecimal.valueOf(12.21);
        };
    }

    public BigDecimal getPrice(SubscriptionTermination termination) {
        BigDecimal basePrice = getBasePrice();
        return switch (termination) {
            case MONTHLY_SUBSCRIPTION -> basePrice;
            case SUBSCRIPTION_TO_THE_QUARTER ->
                    basePrice.multiply(BigDecimal.valueOf(3)).multiply(BigDecimal.valueOf(0.9)); // 10% скидка
            case SUBSCRIPTION_FOR_A_YEAR ->
                    basePrice.multiply(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(0.8)); // 20% скидка
        };
    }
}


