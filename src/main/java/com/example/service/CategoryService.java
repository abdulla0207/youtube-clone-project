package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());

        categoryRepository.save(entity);
        ////set security for admin id
        entity.setId(dto.getId());
        return dto;
    }

    public List<CategoryDTO> getList() {
        Iterable<CategoryEntity> entities = categoryRepository.findAll();

        List<CategoryDTO> dtos = new ArrayList<>();
        entities.forEach(category -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setCreatedDate(category.getCreatedDate());
            dtos.add(dto);
        });
        return dtos;
    }

}
