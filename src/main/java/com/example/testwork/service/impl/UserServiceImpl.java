package com.example.testwork.service.impl;

import com.example.testwork.dto.request.UserDataRequest;
import com.example.testwork.dto.response.UserDataCreateResponse;
import com.example.testwork.dto.response.UserDataInfoResponse;
import com.example.testwork.mapper.UserMapper;
import com.example.testwork.repository.UserRepository;
import com.example.testwork.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.testwork.util.constant.ConstantProject.USER_WITH_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDataCreateResponse createUser(UserDataRequest dto) {
        return userMapper.userEntityToCreateResponse(userRepository.save(userMapper.createRequestToEntity(dto)));
    }

    @Override
    public UserDataInfoResponse getUserById(Long id) {
        return userMapper.userEntityToUserInfoResponse(userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(USER_WITH_ID_NOT_FOUND.formatted(id))
        ));
    }

    @Override
    public void updateUser(Long id) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
