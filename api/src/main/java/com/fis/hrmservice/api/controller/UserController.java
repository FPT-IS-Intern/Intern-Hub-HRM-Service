package com.fis.hrmservice.api.controller;

import com.fis.hrmservice.api.dto.request.FilterRequest;
import com.fis.hrmservice.api.dto.request.RegisterUserRequest;
import com.fis.hrmservice.api.dto.response.FilterResponse;
import com.fis.hrmservice.api.mapper.UserApiMapper;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.FilterUserUseCase;
import com.fis.hrmservice.domain.port.input.RegisterUserUseCase;
import com.fis.hrmservice.domain.port.input.UserProfileUseCase;
import com.fis.hrmservice.domain.usecase.command.FilterUserCommand;
import com.fis.hrmservice.domain.usecase.command.RegisterUserCommand;
import com.intern.hub.library.common.annotation.EnableGlobalExceptionHandler;
import com.intern.hub.library.common.dto.ResponseApi;
import com.intern.hub.starter.security.annotation.HasPermission;
import com.intern.hub.starter.security.dto.Scope;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("hrm-serice/api/users")
@EnableGlobalExceptionHandler
@Slf4j
@Tag(name = "User Management", description = "APIs for user registration and management")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final FilterUserUseCase filterUserUseCase;
    private final UserApiMapper userApiMapper;
    private final UserProfileUseCase userProfileUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, FilterUserUseCase filterUserUseCase, UserApiMapper userApiMapper, UserProfileUseCase userProfileUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.filterUserUseCase = filterUserUseCase;
        this.userApiMapper = userApiMapper;
        this.userProfileUseCase = userProfileUseCase;
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseApi<?> registerUser(
            @RequestPart("userInfo") RegisterUserRequest request,
            @RequestPart("avatarFile") MultipartFile avatarFile,
            @RequestPart("cvFile") MultipartFile cvFile
    ) {

        log.info("Got avatar file: {}, cv file: {}", avatarFile.getOriginalFilename(), cvFile.getOriginalFilename());

        request.setAvatar(avatarFile);
        request.setCv(cvFile);

        RegisterUserCommand command = userApiMapper.toCommand(request);
        UserModel user = registerUserUseCase.registerUser(command);

        return ResponseApi.ok(userApiMapper.toResponse(user));
    }

    @GetMapping("/{userId}")
    public ResponseApi<?> getUserById(@PathVariable Long userId) {
        log.info("Get user request for ID: {}", userId);
        return ResponseApi.ok(null);
    }

    @PutMapping("/{userId}")
    public ResponseApi<?> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody RegisterUserRequest request) {
        log.info("Update user request for ID: {}", userId);
        return ResponseApi.ok(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseApi<?> deactivateUser(@PathVariable Long userId) {
        log.info("Deactivate user request for ID: {}", userId);
        return ResponseApi.ok(null);
    }

    @PostMapping("/filter")
//    @HasPermission(resource = "user", action = "read", scope = Scope.ALL)
    public ResponseApi<List<FilterResponse>> filterUsers(
            @RequestBody FilterRequest request
            ){
        FilterUserCommand filterUserCommand = userApiMapper.toCommand(request);
        List<UserModel> userModelList = filterUserUseCase.filterUsers(filterUserCommand);
        return ResponseApi.ok(userApiMapper.toFilterResponseList(userModelList));
    }

    @GetMapping("/profile/{userId}")
    public ResponseApi<?> getUserProfile(@PathVariable("userId") Long userId) {
        UserModel userModel = userProfileUseCase.getUserProfile(userId);
        return ResponseApi.ok(userApiMapper.toResponse(userModel));
    }
}
