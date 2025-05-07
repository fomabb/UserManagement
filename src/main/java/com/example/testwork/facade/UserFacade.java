package com.example.testwork.facade;

import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.exceptionhandler.exception.BusinessException;
import com.example.testwork.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserDataCreateResponse createUser(UserDataCreateRequest request) {
        log.info("Начало проверки подтверждения пароля пользователя");
        if (request.getPassword().equals(request.getConfirmPassword())) {
            log.info("Пароль пользователя подтвержден успешно");
            return userService.createUser(request);
        } else {
            log.warn("Пароль пользователя не совпадает с введенным изначально");
            throw new BusinessException("Password not valid");
        }
    }
}
