package com.example.testwork.entity;

import com.example.testwork.entity.enumerate.SubscriptionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "subscription_stats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionStats {

    @Id
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    private Integer popularity;

    private BigDecimal price;
}
