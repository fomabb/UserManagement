package com.example.testwork.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Запрос на обновление информации о пользователе.")
public class UpdateUserRequest {

    @Schema(description = "Имя пользователя.", example = "Фома")
    private String firstName;

    @Schema(description = "Фамилия пользователя.", example = "Фоменко")
    private String lastName;

    @Schema(description = "Адрес электронной почты пользователя.", example = "foma@gmail.com")
    @Email(message = "Адрес электронной почты должен быть в формате user@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 100 символов")
    private String email;
}
