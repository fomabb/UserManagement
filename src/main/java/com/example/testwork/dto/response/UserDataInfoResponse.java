package com.example.testwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
