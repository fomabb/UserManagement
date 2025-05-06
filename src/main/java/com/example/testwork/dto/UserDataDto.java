package com.example.testwork.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDataDto {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
