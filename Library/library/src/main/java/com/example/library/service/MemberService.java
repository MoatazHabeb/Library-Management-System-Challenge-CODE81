package com.example.library.service;

import com.example.library.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto createMember(MemberDto dto);
    MemberDto updateMemberByNationalId(Long nationalId, MemberDto dto);
    void deleteMemberByNationalId(Long nationalId);
    MemberDto getMemberByNationalId(Long nationalId);
    List<MemberDto> getAllMembers();
}
