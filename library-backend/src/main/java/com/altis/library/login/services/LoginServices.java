package com.altis.library.login.services;

import com.altis.library.login.models.dtos.LoginRequestDTO;
import com.altis.library.login.models.dtos.LoginResponseDTO;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServices(
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

        return new LoginResponseDTO(
                user.getId(),
                user.getNameFull(),
                user.getEmail(),
                user.getIsAdmin()
        );
    }
}