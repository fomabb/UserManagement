package com.example.testwork.controller;

import com.example.testwork.dto.request.SubscriptionDataCreateRequest;
import com.example.testwork.dto.response.SubscriptionDataCreateResponse;
import com.example.testwork.dto.response.SubscriptionTopResponse;
import com.example.testwork.dto.response.SubscriptionUserDataResponse;
import com.example.testwork.exceptionhandler.response.CommonExceptionResponse;
import com.example.testwork.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Управление подписками", description = "`Интерфейс для управления подписками пользователя`")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(
            summary = "Создать подписку для пользователя.",
            description = """
                    `
                    Добавляет новую подписку для указанного пользователя.
                    Принимает идентификатор пользователя и данные для подписки.
                    Возвращает статус 201 Created при успешном добавлении.
                    `
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionDataCreateRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "`Подписка успешно добавлена`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionDataCreateResponse.class))
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
    @PostMapping("/users/{id}")
    public ResponseEntity<SubscriptionDataCreateResponse> createSubscription(
            @PathVariable("id") Long userId,
            @RequestBody SubscriptionDataCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.addSubscriptionByUserById(userId, request));
    }

    @Operation(
            summary = "Получить подписки пользователя по ID.",
            description = """
                    `
                    Возвращает список подписок для указанного пользователя по его идентификатору.
                    Возвращает статус 200 OK при успешном получении данных.
                    `
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "`Подписки пользователя найдены`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionUserDataResponse.class))
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
    @GetMapping("/users/{id}")
    public ResponseEntity<List<SubscriptionUserDataResponse>> getSubscriptionByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByUserId(userId));
    }

    @Operation(
            summary = "Удалить подписку пользователя.",
            description = """
                    `
                    Удаляет подписку с указанным идентификатором для указанного пользователя.
                    Возвращает статус 204 No Content при успешном удалении.
                    `
                    """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "`Подписка успешно удалена`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())
                    ),
                    @ApiResponse(responseCode = "404", description = "`Подписка или пользователь не найдены`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @DeleteMapping("/{sub_id}/users/{id}")
    public ResponseEntity<Void> deleteSubscriptionByIdsUserSubscription(
            @PathVariable("sub_id") Long subId,
            @PathVariable("id") Long userId
    ) {
        subscriptionService.deleteSubscriptionByIdsUserSubscription(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получить три самых популярных подписки.",
            description = """
                    `
                    Возвращает список из трех самых популярных подписок.
                    Возвращает статус 200 OK при успешном получении данных.
                    `
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "`Популярные подписки найдены`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SubscriptionTopResponse.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "`Ошибка сервера`",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommonExceptionResponse.class))
                    )
            }
    )
    @GetMapping("/top")
    public List<SubscriptionTopResponse> getTop3PopularSubscriptions() {
        return subscriptionService.getTop3PopularSubscriptions();
    }
}
