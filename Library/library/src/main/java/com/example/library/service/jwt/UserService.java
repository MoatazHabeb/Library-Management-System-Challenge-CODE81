package com.example.library.service.jwt;

import com.example.library.dto.jwt.UserDto;
import com.example.library.model.usermodel.Users;
import jakarta.transaction.SystemException;

public interface UserService {
    Users getUserbyEmail(String email) throws SystemException;
    Users checkUserExistByToken(String token) throws SystemException;
    void createUser (UserDto userDto) throws SystemException;
}
