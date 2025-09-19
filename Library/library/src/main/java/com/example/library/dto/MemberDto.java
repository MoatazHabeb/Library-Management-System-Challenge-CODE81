package com.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Long nationalId;
}
