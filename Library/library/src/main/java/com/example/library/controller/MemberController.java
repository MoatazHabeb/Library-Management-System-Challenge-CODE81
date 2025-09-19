package com.example.library.controller;

import com.example.library.dto.MemberDto;
import com.example.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/createMember")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto dto) {
        return ResponseEntity.ok(memberService.createMember(dto));
    }

    @PutMapping("/updateMember/{nationalId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> updateMember(
            @PathVariable Long nationalId,
            @RequestBody MemberDto dto) {
        return ResponseEntity.ok(memberService.updateMemberByNationalId(nationalId, dto));
    }

    @DeleteMapping("/deleteMember/{nationalId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteMember(@PathVariable Long nationalId) {
        memberService.deleteMemberByNationalId(nationalId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMember/{nationalId}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN' , 'STAFF')")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long nationalId) {
        return ResponseEntity.ok(memberService.getMemberByNationalId(nationalId));
    }

    @GetMapping("/getAllMembers")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }
}
