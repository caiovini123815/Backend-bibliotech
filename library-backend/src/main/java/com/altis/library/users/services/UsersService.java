package com.altis.library.users.services;

import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository,
                        PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public users create(users user) {

        if (usersRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (usersRepository.existsByCpf(user.getCpf())) {
            throw new RuntimeException("CPF already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return usersRepository.save(user);
    }

    public List<users> findAll() {
        return usersRepository.findAll();
    }

    public users findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public users update(Long id, users userData) {

        users user = findById(id);

        user.setNameFull(userData.getNameFull());
        user.setEmail(userData.getEmail());
        user.setCpf(userData.getCpf());
        user.setPhone(userData.getPhone());
        user.setBirthDate(userData.getBirthDate());
        user.setAddress(userData.getAddress());

        user.setPassword(passwordEncoder.encode(userData.getPassword()));

        user.setIsAdmin(userData.getIsAdmin());
        user.setIsDisable(userData.getIsDisable());

        return usersRepository.save(user);
    }

    public void delete(Long id) {

        users user = findById(id);

        usersRepository.delete(user);
    }
}