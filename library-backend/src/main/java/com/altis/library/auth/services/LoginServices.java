package com.altis.library.auth.services;

import com.altis.library.auth.models.dtos.LoginRequestDTO;
import com.altis.library.auth.models.dtos.LoginResponseDTO;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenServices tokenServices;

    public LoginServices(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            TokenServices tokenServices
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenServices = tokenServices;


    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginData) {

        users user = usersRepository
                .findByEmail(loginData.email())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                loginData.password(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }


        if (Boolean.TRUE.equals(user.getIsDisable())) {
            throw new RuntimeException(
                    "User is disabled and cannot login"
            );
        }
        String token = tokenServices.generateToken(user);

        return new LoginResponseDTO(
                token
        );
    }
}