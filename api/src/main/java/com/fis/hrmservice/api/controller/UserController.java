package com.fis.hrmservice.api.controller;

import com.fis.hrmservice.api.dto.request.RegisterUserRequest;
import com.fis.hrmservice.api.dto.response.UserResponse;
import com.fis.hrmservice.api.mapper.UserApiMapper;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.input.RegisterUserUseCase;
import com.fis.hrmservice.domain.usecase.command.RegisterUserCommand;
import com.intern.hub.library.common.annotation.EnableGlobalExceptionHandler;
import com.intern.hub.library.common.dto.ResponseApi;
import com.intern.hub.library.common.dto.ResponseStatus;
import com.intern.hub.starter.security.annotation.EnableSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@EnableSecurity
@RequiredArgsConstructor
@EnableGlobalExceptionHandler
@Slf4j
@Tag(name = "User Management", description = "APIs for user registration and management")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final UserApiMapper userApiMapper;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Register new user", description = "Register a new intern or staff member")
    public ResponseApi<HttpStatus> registerUser(
            @Valid @ModelAttribute RegisterUserRequest request) {

        log.info("Received registration request for email: {}", request.getEmail());

        RegisterUserCommand command = userApiMapper.toCommand(request);

        UserModel registeredUser = registerUserUseCase.registerUser(command);

        UserResponse response = UserResponse.fromModel(registeredUser);

        log.info("Successfully registered user with ID: {}", registeredUser.getUserId());

        return ResponseApi.of(
                new ResponseStatus("402", "User registered successfully"),
                HttpStatus.CREATED,
                ResponseApi.ok(response).metaData()
                );
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieve user information by user ID")
    public ResponseEntity<ResponseApi<UserResponse>> getUserById(@PathVariable Long userId) {
        log.info("Get user request for ID: {}", userId);
        return ResponseEntity.ok(ResponseApi.ok(null));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update user information")
    public ResponseEntity<ResponseApi<UserResponse>> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody RegisterUserRequest request) {
        log.info("Update user request for ID: {}", userId);
        return ResponseEntity.ok(ResponseApi.ok(null));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Deactivate user", description = "Deactivate a user account")
    public ResponseEntity<ResponseApi<Void>> deactivateUser(@PathVariable Long userId) {
        log.info("Deactivate user request for ID: {}", userId);
        return ResponseEntity.ok(ResponseApi.ok(null));
    }
}
