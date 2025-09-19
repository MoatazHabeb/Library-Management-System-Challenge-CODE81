package com.example.library.service.jwt;

import com.example.library.config.jwt.TokenHandler;
import com.example.library.dto.jwt.UserDto;
import com.example.library.mapper.UserMapper;
import com.example.library.model.usermodel.Roles;
import com.example.library.model.usermodel.Users;
import com.example.library.repo.jwt.RolesRepository;
import com.example.library.repo.jwt.UserRepository;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Users getUserbyEmail(String email) throws SystemException {
        Users users = userRepository.findByEmail(email);


        if (users == null) {

            throw new RuntimeException("error.invalid.email");
        }

        return users;
    }



    @Override
    public Users checkUserExistByToken(String token) throws SystemException {
        String email = tokenHandler.getSubject(token);

        if (Objects.isNull(email)) {
            return null;
        }

        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(UserDto userDto) throws SystemException {
        if (userDto.getId() != null) {
            throw new SystemException("id must be null");
        }
        Users userExits = userRepository.findByEmail(userDto.getEmail());
        if (userExits != null) {
            throw new RuntimeException("error.userExist");
        }

        Users users = UserMapper.USER_MAPPER.toEntity(userDto);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Roles role = rolesRepository.findByCode("ROLE_STAFF");
        if (role == null) {
            throw new SystemException("role not exist");
        }
        List<Roles> roles = List.of(role);
        users.setRoles(roles);
       userRepository.save(users);


    }


}
