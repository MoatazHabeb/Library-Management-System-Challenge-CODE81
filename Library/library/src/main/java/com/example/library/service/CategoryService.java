package com.example.library.service;

import com.example.library.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto dto);
    CategoryDto updateategoryById(Long id, CategoryDto dto);
    void deleteCategoryById(Long id);
    List<CategoryDto> getAllCategories();
}
