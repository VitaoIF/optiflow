package com.optiflow.service;

import com.optiflow.dto.request.UserRequest;
import com.optiflow.dto.response.UserResponse;
import com.optiflow.entities.User;
import com.optiflow.exceptions.custom.EmailAlreadyExistsException;
import com.optiflow.mapper.UserMapper;
import com.optiflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest userRequest){
        if (userRepository.existsByEmail(userRequest.email())){
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        User user = UserMapper.toEntity(userRequest);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        User saved = userRepository.save(user);
        return UserMapper.toUserResponse(saved);
    }

    public Page<UserResponse> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserMapper::toUserResponse);
    }

    public UserResponse findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return UserMapper.toUserResponse(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
