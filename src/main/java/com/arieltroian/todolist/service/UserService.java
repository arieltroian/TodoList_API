package com.arieltroian.todolist.service;

import com.arieltroian.todolist.dto.CreateUserRequest;
import com.arieltroian.todolist.entity.User;
import com.arieltroian.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Esse email já existe!");
        }

        User user = new User(request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}
