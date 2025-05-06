package com.example.testwork.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserRequest {

    private String firstName;

    private String lastName;

    @Email(message = "Адрес электронной почты должен быть в формате user@gmail.com")
    @Size(min = 5, max = 100, message = "Адрес электронной почты должен содержать от 5 до 100 символов")
    private String email;
}
