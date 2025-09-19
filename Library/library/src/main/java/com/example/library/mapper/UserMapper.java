package com.example.library.mapper;

import com.example.library.dto.jwt.UserDto;
import com.example.library.model.usermodel.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    Users toEntity(UserDto userDto);
    List<Users> toEntityList(List<UserDto> userDtoList);


    UserDto toDto(Users users);
    List<UserDto> toDtoList( List<Users> clientList);
}
