package com.fis.hrmservice.api.controller.user;

import com.fis.hrmservice.api.dto.request.FilterRequest;
import com.fis.hrmservice.api.dto.request.RegisterUserRequest;
import com.fis.hrmservice.api.dto.request.UpdateProfileRequest;
import com.fis.hrmservice.api.dto.response.FilterResponse;
import com.fis.hrmservice.api.dto.response.UserResponse;
import com.fis.hrmservice.api.mapper.UserApiMapper;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.CreateAuthIdentityPort;
import com.fis.hrmservice.domain.usecase.command.user.FilterUserCommand;
import com.fis.hrmservice.domain.usecase.command.user.RegisterUserCommand;
import com.fis.hrmservice.domain.usecase.implement.user.*;
import com.intern.hub.library.common.annotation.EnableGlobalExceptionHandler;
import com.intern.hub.library.common.dto.ResponseApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("hrm-service/users")
@EnableGlobalExceptionHandler
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4205"})
@Slf4j
@Tag(name = "User Management", description = "APIs for user registration and management")
public class UserController {

  @Autowired private RegisterUserUseCaseImpl registerUserUseCase;

  @Autowired private FilterUseCaseImpl filterUserUseCase;

  @Autowired private UserApiMapper userApiMapper;

  @Autowired private UserProfileUseCaseImpl userProfileUseCase;

  @Autowired private UserApproval approvalUser;

  @Autowired private UserRejection rejectionUser;

  @Autowired private UserSuspension userSuspension;

  @Autowired private CreateAuthIdentityPort createAuthIdentityPort;

  @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseApi<?> registerUser(
      @RequestPart("userInfo") RegisterUserRequest request,
      @RequestPart(value = "avatarFile", required = false) MultipartFile avatarFile,
      @RequestPart(value = "cvFile", required = false) MultipartFile cvFile) {

    if (avatarFile != null && !avatarFile.isEmpty()) {
      request.setAvatar(avatarFile);
    }
    if (cvFile != null && !cvFile.isEmpty()) {
      request.setCv(cvFile);
    }

    RegisterUserCommand command = userApiMapper.toCommand(request);
    UserModel user = registerUserUseCase.registerUser(command);
    //
    //    // Gọi Feign Client để tạo auth identity
    //    try
    //      createAuthIdentityPort.createAuthIdentity(user.getUserId(), user.getCompanyEmail());
    //      log.info("Auth identity created successfully for userId: {}", user.getUserId());
    //    } catch (Exception e) {
    //      log.error("Call Auth Service failed: {}", e.getMessage(), e);
    //      throw new RuntimeException("Create auth identity failed: " + e.getMessage());
    //    }

    return ResponseApi.ok(userApiMapper.toResponse(user));
  }

  @PostMapping("/filter")
  public ResponseApi<List<FilterResponse>> filterUsers(@RequestBody FilterRequest request) {
    FilterUserCommand filterUserCommand = userApiMapper.toCommand(request);
    List<UserModel> userModelList = filterUserUseCase.filterUsers(filterUserCommand);
    return ResponseApi.ok(userApiMapper.toFilterResponseList(userModelList));
  }

  // cái này dùng cho admin xem profile của 1 user cụ thể nào đó
  @GetMapping("/profile/{userId}")
  public ResponseApi<?> getUserProfile(@PathVariable Long userId) {
    log.info("Get user profile for ID: {}", userId);
    UserModel userModel = userProfileUseCase.getUserProfile(userId);
    return ResponseApi.ok(userApiMapper.toResponse(userModel));
  }

  // -------------------- Approval and Rejection Endpoints -------------------//
  @PutMapping("/approval/{userId}")
  public ResponseApi<?> approveUser(@PathVariable Long userId) {
    log.info("Approve user request for ID: {}", userId);
    UserModel userModel = approvalUser.approveUser(userId);
    return ResponseApi.ok(
        "Đã approve user " + userModel.getFullName() + " với status: " + userModel.getSysStatus());
  }

  @PutMapping("/rejection/{userId}")
  public ResponseApi<?> rejectUser(@PathVariable Long userId) {
    log.info("Reject user request for ID: {}", userId);
    UserModel userReject = rejectionUser.rejectUser(userId);
    return ResponseApi.ok(
        "Đã reject user " + userReject.getFullName() + " với status: " + userReject.getSysStatus());
  }

  // =====================================================================================
  @PatchMapping(value = "/me/{userId}/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseApi<?> updateProfile(
      @RequestPart("userInfo") UpdateProfileRequest request,
      @RequestPart(value = "cvFile", required = false) MultipartFile cvFile,
      @RequestPart(value = "avatarFile", required = false) MultipartFile avatarFile,
      @PathVariable long userId) { // sau này hoàn thành api gateway se sửa sau
    log.info("Update profile for user ID: {}", userId);
    if (cvFile != null && !cvFile.isEmpty()) {
      request.setCvFile(cvFile);
    }
    if (avatarFile != null && !avatarFile.isEmpty()) {
      request.setAvatarFile(avatarFile);
    }
    userProfileUseCase.updateProfileUser(userApiMapper.toUpdateUserProfileCommand(request), userId);
    return ResponseApi.ok("Update user profile thành công");
  }

  @PutMapping("/suspension/{userId}")
  public ResponseApi<UserResponse> suspendUser(@PathVariable Long userId) {
    UserModel userModel = userSuspension.suspendUser(userId);
    return ResponseApi.ok(userApiMapper.toResponse(userModel));
  }

  @GetMapping("/total-intern")
  public ResponseApi<Integer> totalInternship() {
    Integer totalIntern = approvalUser.totalIntern();
    return ResponseApi.ok(totalIntern);
  }

  @GetMapping("/internship-changing")
  public ResponseApi<String> internshipChanging() {
    Integer internshipChanging = approvalUser.internshipChanging();

    String message;

    if (internshipChanging > 0) {
      message = "↗ " + internshipChanging + " So với tháng trước";
    } else {
      message = "↘ " + internshipChanging + " So vơi tháng trước";
    }

    return ResponseApi.ok(message);
  }
}
