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

    public BigDecimal getPrice() {
        return switch (this) {
            case YOUTUBE_PREMIUM -> BigDecimal.valueOf(11.99);
            case VK_MUSIC -> BigDecimal.valueOf(4.44);
            case YANDEX_PLUS -> BigDecimal.valueOf(9.01);
            case NETFLIX -> BigDecimal.valueOf(15.11);
            case DISNEY_PLUS -> BigDecimal.valueOf(7.92);
            case SPOTIFY -> BigDecimal.valueOf(12.21);
        };
    }

    public Integer getPopularity() {
        return switch (this) {
            case YOUTUBE_PREMIUM -> 2100;
            case VK_MUSIC -> 1200;
            case YANDEX_PLUS -> 1980;
            case NETFLIX -> 2400;
            case DISNEY_PLUS -> 980;
            case SPOTIFY -> 1621;
        };
    }
}


