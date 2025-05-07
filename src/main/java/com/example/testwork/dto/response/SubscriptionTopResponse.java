package com.example.testwork.dto.response;

import com.example.testwork.entity.enumerate.SubscriptionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Ответ с информацией о самых популярных подписках.")
public class SubscriptionTopResponse {

    @Schema(description = "Название типа подписки.", example = "NETFLIX")
    private SubscriptionType name;

    @Schema(description = "Популярность подписки (количество подписчиков).", example = "2400")
    private int popularity;
}
