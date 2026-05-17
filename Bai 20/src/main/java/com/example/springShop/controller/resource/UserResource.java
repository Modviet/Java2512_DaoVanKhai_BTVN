package com.example.springShop.controller.resource;

import com.example.springShop.dto.UserListItemDto;
import com.example.springShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserListItemDto>> getAllUser(){
        return ResponseEntity.ok(userService.getUsers(null, null, 0 ,1000));
    }
}
