package com.example.library.controller.user;

import com.example.library.dto.jwt.TokenDto;
import com.example.library.dto.jwt.UserDto;
import com.example.library.dto.jwt.UserLoginDto;
import com.example.library.service.jwt.AuthService;
import com.example.library.service.jwt.UserService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Validated
public class UserController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody UserLoginDto userLoginDto) throws SystemException {

        return ResponseEntity.ok(authService.login(userLoginDto));
    }

    @PostMapping("/create-user")
    ResponseEntity<Void> createUser(@RequestBody @Valid UserDto userDto) throws SystemException {
        userService.createUser(userDto);
        return  ResponseEntity.created(URI.create("/addUserWithRole")).build();
    }
}
