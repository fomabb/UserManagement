package com.example.testwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDataInfoResponse {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    @JsonFormat(pattern = "dd.MM.yyyy HH:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "dd.MM.yyyy HH:ss")
    private LocalDateTime updateAt;
}
