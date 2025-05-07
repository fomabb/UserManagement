package com.example.testwork.service.impl;

import com.example.testwork.dto.request.UpdateUserRequest;
import com.example.testwork.dto.request.UserDataCreateRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.entity.User;
import com.example.testwork.mapper.UserMapper;
import com.example.testwork.repository.UserRepository;
import com.example.testwork.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.testwork.util.constant.ConstantProject.USER_WITH_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDataCreateResponse createUser(UserDataCreateRequest dto) {
        log.info("Создание пользователя с email: {}", dto.getEmail());
        UserDataCreateResponse response = userMapper.userEntityToCreateResponse(
                userRepository.save(userMapper.createRequestToEntity(dto))
        );
        log.info("Пользователь создан с ID: {}", response.getUserId());
        return response;
    }

    @Override
    public UserDataInfoResponse getUserById(Long id) {
        log.info("Запрос пользователя с ID: {}", id);
        UserDataInfoResponse response = userMapper.userEntityToUserInfoResponse(
                userRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(id))
                )
        );
        log.info("Пользователь найден: {}", response);
        return response;
    }

    @Override
    @Transactional
    public void updateUser(Long id, UpdateUserRequest dto) {
        log.info("Обновление пользователя с ID: {}", id);
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(id))
        );
        User updatedUser = userMapper.updateUserDtoToUpdateUserEntity(dto);
        userRepository.save(userMapper.buildUpdateUserForSave(existingUser, updatedUser));
        log.info("Пользователь с ID: {} обновлен", id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Удаление пользователя с ID: {}", id);
        userRepository.deleteById(id);
        log.info("Пользователь с ID: {} удален", id);
    }
}
