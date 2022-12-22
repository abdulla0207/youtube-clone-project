package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/create")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.warn("createCategory : {} " + categoryDTO);
        CategoryDTO result = categoryService.create(categoryDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public List<CategoryDTO> show() {
        return categoryService.getList();
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.warn(" CATEGORY DELETE ");
        String delete = categoryService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updater(@Valid @PathVariable("id") Integer id,
                                     @RequestBody CategoryDTO categoryDTO) {
        log.warn("UPDATE CATEGORY : {} " + categoryDTO);
        CategoryDTO result = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(result);
    }
}
