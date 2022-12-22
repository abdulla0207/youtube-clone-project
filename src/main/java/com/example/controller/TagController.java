package com.example.controller;

import com.example.dto.TagDTO;
import com.example.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/admin/create")
    public ResponseEntity<TagDTO> create(@Valid @RequestBody TagDTO tagDTO) {
        TagDTO result = tagService.create(tagDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public List<TagDTO> show() {
        return tagService.getList();
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        String delete = tagService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updater(@Valid @PathVariable("id") Integer id,
                                     @RequestBody TagDTO tagDTO) {
        TagDTO result = tagService.update(id, tagDTO);
        return ResponseEntity.ok(result);
    }
}
