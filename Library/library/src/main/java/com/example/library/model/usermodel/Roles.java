package com.example.library.model.usermodel;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Roles extends BaseEntity{
    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;


}
