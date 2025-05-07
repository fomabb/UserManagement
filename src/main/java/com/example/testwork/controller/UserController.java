package com.example.testwork.controller;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.exceptionhandler.response.CommonExceptionResponse;
import com.example.testwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Управление пользователями", description = "`Интерфейс для управления пользователями`")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Добавить пользователя.",
            description = """
                    `
                    Создаёт нового пользователя с указанными данными.
                    Возвращает статус 201 Created при успешном добавлении.
                    `
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDataCreateRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "`Пользователь успешно добавлен`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDataCreateResponse.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "`Некорректный запрос`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UserDataCreateResponse> createUser(@RequestBody @Valid UserDataCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @Operation(
            summary = "Получить информацию о пользователе по ID.",
            description = """
                    `
                    Возвращает информацию о пользователе с указанным идентификатором.
                    Возвращает статус 200 OK при успешном получении данных.
                    `
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "`Пользователь найден`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDataInfoResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "`Пользователь не найден`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDataInfoResponse> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(
            summary = "Обновить информацию о пользователе.",
            description = """
                    `
                    Обновляет данные пользователя с указанным идентификатором.
                    Возвращает статус 202 Accepted при успешном обновлении.
                    `
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateUserRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "202", description = "`Данные пользователя успешно обновлены`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())
                    ),
                    @ApiResponse(responseCode = "400", description = "`Некорректный запрос`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "`Пользователь не найден`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        userService.updateUser(userId, request);
        return ResponseEntity.accepted().build();
    }

    @Operation(
            summary = "Удалить пользователя по ID.",
            description = """
                    `
                    Удаляет пользователя с указанным идентификатором.
                    Возвращает статус 204 No Content при успешном удалении.
                    `
                    """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "`Пользователь успешно удален`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())
                    ),
                    @ApiResponse(responseCode = "404", description = "`Пользователь не найден`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
