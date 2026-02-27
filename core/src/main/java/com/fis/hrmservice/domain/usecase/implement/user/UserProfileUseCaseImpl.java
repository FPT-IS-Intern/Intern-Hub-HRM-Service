package com.fis.hrmservice.domain.usecase.implement.user;

import com.fis.hrmservice.domain.model.user.AvatarModel;
import com.fis.hrmservice.domain.model.user.CvModel;
import com.fis.hrmservice.domain.model.user.UserModel;
import com.fis.hrmservice.domain.port.output.user.AvatarRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.CvRepositoryPort;
import com.fis.hrmservice.domain.port.output.user.FileStoragePort;
import com.fis.hrmservice.domain.port.output.user.UserRepositoryPort;
import com.fis.hrmservice.domain.service.UserValidationService;
import com.fis.hrmservice.domain.usecase.command.user.UpdateUserProfileCommand;
import com.fis.hrmservice.domain.utils.UpdateHelper;
import com.intern.hub.library.common.exception.ConflictDataException;
import com.intern.hub.library.common.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileUseCaseImpl {

    UserRepositoryPort userRepositoryPort;

    UserValidationService userValidationService;

    FileStoragePort fileStoragePort;

    AvatarRepositoryPort avatarRepositoryPort;

    CvRepositoryPort cvRepositoryPort;

    public UserModel getUserProfile(Long userId) {

        return userRepositoryPort
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
    }

    public UserModel updateProfileUser(UpdateUserProfileCommand command, long userId) throws IOException {

        UserModel userModel =
                userRepositoryPort
                        .findById(userId)
                        .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        userValidationService.validateUpdate(command);

        boolean changed = false;

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getFullName(),
                        userModel::getFullName,
                        userModel::setFullName,
                        (oldVal, newVal) -> Objects.equals(trim(oldVal), trim(newVal)));

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getCompanyEmail(),
                        userModel::getCompanyEmail,
                        userModel::setCompanyEmail,
                        (oldVal, newVal) -> Objects.equals(trim(oldVal), trim(newVal)));

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getDateOfBirth(),
                        userModel::getDateOfBirth,
                        userModel::setDateOfBirth,
                        Objects::equals);

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getIdNumber(),
                        userModel::getIdNumber,
                        userModel::setIdNumber,
                        (oldVal, newVal) -> Objects.equals(trim(oldVal), trim(newVal)));

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getAddress(),
                        userModel::getAddress,
                        userModel::setAddress,
                        (oldVal, newVal) -> Objects.equals(trim(oldVal), trim(newVal)));

        changed |=
                UpdateHelper.applyIfChanged(
                        command.getPhoneNumber(),
                        userModel::getPhoneNumber,
                        userModel::setPhoneNumber,
                        (oldVal, newVal) -> Objects.equals(trim(oldVal), trim(newVal)));


        if (command.getCvFile() != null && !command.getCvFile().isEmpty()) {


            String cvUrl = fileStoragePort.upload(
                    command.getCvFile().getBytes(),
                    command.getCvFile().getOriginalFilename(),
                    command.getCvFile().getContentType(),
                    "/cvs/" + userModel.getUserId());



            CvModel cv = cvRepositoryPort.findByUserId(userModel.getUserId());

            if (cv == null) {
                throw new NotFoundException("CV not found for user with id: " + userModel.getUserId());
            }

            cv.setCvUrl(cvUrl);
            cv.setFileName(command.getCvFile().getOriginalFilename());
            cv.setFileSize(command.getCvFile().getSize());
            cv.setFileType(command.getCvFile().getContentType());
            cvRepositoryPort.save(cv);
            changed = true;
        }

        if (command.getAvatarFile() != null && !command.getAvatarFile().isEmpty()) {

            String avatarUrl = fileStoragePort.upload(
                    command.getAvatarFile().getBytes(),
                    command.getAvatarFile().getOriginalFilename(),
                    command.getAvatarFile().getContentType(),
                    "/avatars/" + userModel.getUserId());

            AvatarModel avatar = avatarRepositoryPort.getAvatarByUserId(userModel.getUserId());

            if (avatar == null) {
                throw new NotFoundException("Avatar not found for user with id: " + userModel.getUserId());
            }

            avatar.setAvatarUrl(avatarUrl);
            avatar.setFileName(command.getAvatarFile().getOriginalFilename());
            avatar.setFileSize(command.getAvatarFile().getSize());
            avatar.setFileType(command.getAvatarFile().getContentType());

            avatarRepositoryPort.save(avatar);
            changed = true;
        }

        if (!changed) {
            throw new ConflictDataException("No changes detected in the profile update request");
        }

        return userRepositoryPort.save(userModel);
    }

    public UserModel internalUserProfile(Long userId) {

        UserModel user = userRepositoryPort.internalUserProfile(userId);

        if (user == null) {
            throw new NotFoundException("User with id: " + userId + " not found");
        }

        return switch (user.getSysStatus()) {
            case PENDING -> throw new ConflictDataException("User with id: " + userId + " is pending");
            case REJECTED -> throw new ConflictDataException("User with id: " + userId + " is rejected");
            case SUSPENDED -> throw new ConflictDataException("User with id: " + userId + " is suspended");
            default -> user;
        };
    }

    private static String trim(CharSequence cs) {
        return cs == null ? null : cs.toString().strip();
    }
}
