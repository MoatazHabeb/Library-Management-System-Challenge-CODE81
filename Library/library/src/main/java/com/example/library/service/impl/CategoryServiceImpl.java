package com.example.library.service.impl;

import com.example.library.dto.CategoryDto;
import com.example.library.mapper.CategoryMapper;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.model.Categories;
import java.util.List;
import java.util.stream.Collectors;

import com.example.library.repo.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Categories category = new Categories();
        category.setName(dto.getName());

        if (dto.getParentId() != null) {
            Categories parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        Categories saved = categoryRepository.save(category);
        return CategoryMapper.CATEGORY_MAPPER.toDto(saved);
    }

    @Override
    public CategoryDto updateategoryById(Long id, CategoryDto dto) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(dto.getName());

        if (dto.getParentId() != null) {
            Categories parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null); // reset parent
        }

        Categories updated = categoryRepository.save(category);
        return CategoryMapper.CATEGORY_MAPPER.toDto(updated);
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper.CATEGORY_MAPPER::toDto)
                .collect(Collectors.toList());
    }
}
