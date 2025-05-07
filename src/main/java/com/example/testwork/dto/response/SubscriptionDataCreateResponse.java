package com.example.testwork.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Ответ при создании новой подписки.")
public class SubscriptionDataCreateResponse {

    @Schema(description = "Идентификатор пользователя.", example = "123")
    private Long userId;

    @Schema(description = "Идентификатор подписки.", example = "456")
    private Long subscriptionId;
}
