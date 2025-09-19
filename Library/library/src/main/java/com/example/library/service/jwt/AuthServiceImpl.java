package com.example.library.service.jwt;

import com.example.library.config.jwt.TokenHandler;
import com.example.library.dto.jwt.TokenDto;
import com.example.library.dto.jwt.UserLoginDto;
import com.example.library.model.usermodel.Users;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserService userService;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(UserLoginDto userLoginDto) throws SystemException {
        Users user = userService.getUserbyEmail(userLoginDto.getEmail());
        if (!passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("error.userNotExist");
        }

        List<String> rolse = user.getRoles().stream().map(role -> role.getCode().substring(5)).collect(Collectors.toList());
        return new TokenDto(tokenHandler.createToken(user), rolse);
    }

}
