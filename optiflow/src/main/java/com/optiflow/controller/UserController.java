package com.optiflow.controller;

import com.optiflow.dto.response.UserResponse;
import com.optiflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable){
        Page<UserResponse> userResponses = userService.findAll(pageable);
        return ResponseEntity.ok().body(userResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        UserResponse userResponse = userService.findById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
