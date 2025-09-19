package com.example.library.mapper;

import com.example.library.dto.MemberDto;
import com.example.library.dto.jwt.UserDto;
import com.example.library.model.Member;
import com.example.library.model.usermodel.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface MemberMapper {
    MemberMapper MEMBER_MAPPER = Mappers.getMapper(MemberMapper.class);
    Member toEntity(MemberDto memberDto);
    List<Member> toEntityList(List<MemberDto> memberDtos);


    MemberDto toDto(Member member);
    List<MemberDto> toDtoList( List<Member> members);
}
