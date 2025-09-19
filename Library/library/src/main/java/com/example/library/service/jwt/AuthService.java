package com.example.library.service.jwt;

import com.example.library.dto.jwt.TokenDto;
import com.example.library.dto.jwt.UserLoginDto;
import jakarta.transaction.SystemException;

public interface AuthService {
    TokenDto login(UserLoginDto userLoginDto) throws SystemException;

}
