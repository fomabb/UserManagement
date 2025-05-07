package com.example.testwork.dto.request;

import com.example.testwork.entity.enumerate.SubscriptionTermination;
import com.example.testwork.entity.enumerate.SubscriptionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Запрос на создание новой подписки.")
public class SubscriptionDataCreateRequest {

    @Schema(description = "Название типа подписки.", example = "NETFLIX")
    private SubscriptionType subscriptionName;

    @Schema(description = "Тип прекращения подписки.", example = "SUBSCRIPTION_TO_THE_QUARTER")
    private SubscriptionTermination subscriptionTermination;
}
