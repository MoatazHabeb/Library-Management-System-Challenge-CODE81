package com.example.library.model;


import com.example.library.model.usermodel.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Categories parent;   // hierarchical categories

    @OneToMany(mappedBy = "parent")
    private List<Categories> children = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;
}
