package com.example.library.service.impl;

import com.example.library.dto.MemberDto;
import com.example.library.mapper.MemberMapper;
import com.example.library.model.Member;
import com.example.library.repo.MemberRepository;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto dto) {
        if (memberRepository.existsByNationalId(dto.getNationalId())) {
            throw new RuntimeException("error.MemberExist");
        }
        Member member = MemberMapper.MEMBER_MAPPER.toEntity(dto);
        return MemberMapper.MEMBER_MAPPER.toDto(memberRepository.save(member));
    }

    @Override
    public MemberDto updateMemberByNationalId(Long nationalId, MemberDto dto) {
        Member member = memberRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("Member not found with nationalId: " + nationalId));

        Member updated = MemberMapper.MEMBER_MAPPER.toEntity(dto);
        updated.setId(member.getId()); // keep original ID
        return MemberMapper.MEMBER_MAPPER.toDto(memberRepository.save(updated));
    }

    @Override
    @Transactional
    public void deleteMemberByNationalId(Long nationalId) {
        if (!memberRepository.existsByNationalId(nationalId)) {
            throw new RuntimeException("Member not found with nationalId: " + nationalId);
        }
        memberRepository.deleteByNationalId(nationalId);
    }

    @Override
    public MemberDto getMemberByNationalId(Long nationalId) {
        Member member = memberRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new RuntimeException("Member not found with nationalId: " + nationalId));
        return MemberMapper.MEMBER_MAPPER.toDto(member);
    }

    @Override
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberMapper.MEMBER_MAPPER::toDto)
                .collect(Collectors.toList());
    }
}
