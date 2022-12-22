package com.example.controller;

import com.example.dto.TagDTO;
import com.example.service.TagService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/admin/create")
    public ResponseEntity<TagDTO> create(@Valid @RequestBody TagDTO tagDTO) {
        log.warn("createTag : {} " + tagDTO);
        TagDTO result = tagService.create(tagDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public List<TagDTO> show() {
        return tagService.getList();
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        log.warn(" DELETE TAG ");
        String delete = tagService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?> updater(@Valid @PathVariable("id") Integer id,
                                     @RequestBody TagDTO tagDTO) {
        log.warn("UPDATE : {} " + tagDTO);
        TagDTO result = tagService.update(id, tagDTO);
        return ResponseEntity.ok(result);
    }
}
