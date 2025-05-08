package com.example.testwork.entity.enumerate;

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
}


