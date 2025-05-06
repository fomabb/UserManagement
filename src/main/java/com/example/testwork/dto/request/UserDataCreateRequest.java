package com.example.testwork.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDataCreateRequest {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия пользователя не может быть пустой")
    private String lastName;

    @Email(message = "Адрес электронной почты должен быть в формате user@gmail.com")
    @Size(min = 5, max = 100, message = "Адрес электронной почты должен содержать от 5 до 100 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    private String email;
}
