package com.altis.library.users.services;

import com.altis.library.auth.models.dtos.ForgotPasswordRequestDTO;
import com.altis.library.auth.models.dtos.RegisterRequestDTO;
import com.altis.library.auth.models.dtos.ResetPasswordRequestDTO;
import com.altis.library.auth.models.dtos.ResetPasswordResponseDTO;
import com.altis.library.users.models.dtos.UsersRequestDTO;
import com.altis.library.users.models.dtos.UsersResponseDTO;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private Long recoveryUserId;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void forgotPassword(ForgotPasswordRequestDTO data) {

        users user = usersRepository
                .findByEmailAndCpf(
                        data.email(),
                        data.cpf()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Email or CPF is invalid"
                        )
                );

        recoveryUserId = user.getId();
    }

    @Transactional
    public UsersResponseDTO create(UsersRequestDTO userData) {

        if (usersRepository.existsByEmail(userData.email())) {
            throw new RuntimeException("Email already registered");
        }

        if (usersRepository.existsByCpf(userData.cpf())) {
            throw new RuntimeException("CPF already registered");
        }

        users user = new users();

        user.setNameFull(userData.nameFull());
        user.setEmail(userData.email());
        user.setCpf(userData.cpf());
        user.setPhone(userData.phone());
        user.setBirthDate(userData.birthDate());
        user.setAddress(userData.address());
        user.setPassword(passwordEncoder.encode(userData.password()));
        user.setIsAdmin(false);
        user.setIsDisable(false);

        users savedUser = usersRepository.save(user);

        return new UsersResponseDTO(
                savedUser.getId(),
                savedUser.getNameFull(),
                savedUser.getPhone(),
                savedUser.getBirthDate(),
                savedUser.getAddress(),
                savedUser.getIsAdmin(),
                savedUser.getIsDisable(),
                savedUser.getCreateDt(),
                savedUser.getUpdateDt()
        );
    }

    @Transactional
    public Page<UsersResponseDTO> findAll(Pageable pageable) {

        return usersRepository
                .findAll(pageable)
                .map(user -> new UsersResponseDTO(
                        user.getId(),
                        user.getNameFull(),
                        user.getPhone(),
                        user.getBirthDate(),
                        user.getAddress(),
                        user.getIsAdmin(),
                        user.getIsDisable(),
                        user.getCreateDt(),
                        user.getUpdateDt()
                ));
    }

    @Transactional
    public users findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Transactional
    public users update(Long id, users userData) {

        users user = findById(id);

        user.setNameFull(userData.getNameFull());
        user.setPhone(userData.getPhone());
        user.setAddress(userData.getAddress());
        user.setIsAdmin(userData.getIsAdmin());
        user.setIsDisable(userData.getIsDisable());

        return usersRepository.save(user);
    }

    @Transactional
    public users partialUpdate(Long id, users userData) {

        users user = findById(id);

        if (userData.getNameFull() != null) {
            user.setNameFull(userData.getNameFull());
        }

        if (userData.getPhone() != null) {
            user.setPhone(userData.getPhone());
        }

        if (userData.getBirthDate() != null) {
            user.setBirthDate(userData.getBirthDate());
        }

        if (userData.getAddress() != null) {
            user.setAddress(userData.getAddress());
        }

        if (userData.getIsAdmin() != null) {
            user.setIsAdmin(userData.getIsAdmin());
        }

        return usersRepository.save(user);
    }
    @Transactional
    public ResetPasswordResponseDTO resetPassword(
            ResetPasswordRequestDTO data
    ) {

        if (recoveryUserId == null) {
            throw new RuntimeException(
                    "User confirmation is required"
            );
        }

        if (!data.newPassword()
                .equals(data.repeatPassword())) {

            throw new RuntimeException(
                    "Passwords do not match"
            );
        }

        users user = usersRepository
                .findById(recoveryUserId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );

        String encryptedPassword =
                passwordEncoder.encode(data.newPassword());

        user.setPassword(encryptedPassword);

        usersRepository.save(user);

        recoveryUserId = null;

        return new ResetPasswordResponseDTO(
                "Password changed successfully",
                encryptedPassword
        );
    }

    @Transactional
    public UsersResponseDTO findByIdResponse(Long id) {

        users user = findById(id);

        return new UsersResponseDTO(
                user.getId(),
                user.getNameFull(),
                user.getPhone(),
                user.getBirthDate(),
                user.getAddress(),
                user.getIsAdmin(),
                user.getIsDisable(),
                user.getCreateDt(),
                user.getUpdateDt()
        );
    }

    @Transactional
    public UsersResponseDTO register(RegisterRequestDTO userData) {

        if (usersRepository.existsByEmail(userData.email())) {
            throw new RuntimeException("Email already registered");
        }

        if (usersRepository.existsByCpf(userData.cpf())) {
            throw new RuntimeException("CPF already registered");
        }

        users user = new users();

        user.setNameFull(userData.nameFull());
        user.setEmail(userData.email());
        user.setCpf(userData.cpf());
        user.setPhone(userData.phone());
        user.setBirthDate(userData.birthDate());
        user.setAddress(userData.address());

        user.setPassword(
                passwordEncoder.encode(userData.password())
        );

        user.setIsAdmin(false);
        user.setIsDisable(false);

        users savedUser = usersRepository.save(user);

        return new UsersResponseDTO(
                savedUser.getId(),
                savedUser.getNameFull(),
                savedUser.getPhone(),
                savedUser.getBirthDate(),
                savedUser.getAddress(),
                savedUser.getIsAdmin(),
                savedUser.getIsDisable(),
                savedUser.getCreateDt(),
                savedUser.getUpdateDt()
        );
    }


    @Transactional
    public UsersResponseDTO disableUser(Long id) {

        users user = findById(id);

        if (Boolean.TRUE.equals(user.getIsDisable())) {
            throw new RuntimeException("User is already disabled");
        }

        user.setIsDisable(true);

        users savedUser = usersRepository.save(user);

        return new UsersResponseDTO(
                savedUser.getId(),
                savedUser.getNameFull(),
                savedUser.getPhone(),
                savedUser.getBirthDate(),
                savedUser.getAddress(),
                savedUser.getIsAdmin(),
                savedUser.getIsDisable(),
                savedUser.getCreateDt(),
                savedUser.getUpdateDt()
        );
    }

    @Transactional
    public UsersResponseDTO enableUser(Long id) {

        users user = findById(id);

        if (Boolean.FALSE.equals(user.getIsDisable())) {
            throw new RuntimeException("User is already enabled");
        }

        user.setIsDisable(false);

        users savedUser = usersRepository.save(user);

        return new UsersResponseDTO(
                savedUser.getId(),
                savedUser.getNameFull(),
                savedUser.getPhone(),
                savedUser.getBirthDate(),
                savedUser.getAddress(),
                savedUser.getIsAdmin(),
                savedUser.getIsDisable(),
                savedUser.getCreateDt(),
                savedUser.getUpdateDt()
        );
    }

}