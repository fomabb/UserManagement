package com.example.testwork.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Schema(description = "Запрос на создание пользователя.")
public class UserDataCreateRequest {

    @Schema(description = "Имя пользователя.", example = "Фома")
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String firstName;

    @Schema(description = "Фамилия пользователя.", example = "Фоменко")
    @NotBlank(message = "Фамилия пользователя не может быть пустой")
    private String lastName;

    @Schema(description = "Адрес электронной почты пользователя.", example = "foma@gmail.com")
    @Email(message = "Адрес электронной почты должен быть в формате user@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    private String email;

    @Schema(description = "Пароль для пользователя", example = "user123")
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 5 до 255 символов")
    private String password;

    @Schema(description = "Подтверждение пароля", example = "user123")
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 5, max = 255, message = "Пароль должен содержать от 5 до 255 символов")
    private String confirmPassword;
}
