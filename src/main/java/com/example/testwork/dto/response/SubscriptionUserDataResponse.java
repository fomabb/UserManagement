package com.example.testwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionUserDataResponse {

    private Long userId;

    private Long subscriptionId;

    private String name;

    private String description;

    private String subscriptionTermination;

    private BigDecimal price;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime expirationDate;
}
