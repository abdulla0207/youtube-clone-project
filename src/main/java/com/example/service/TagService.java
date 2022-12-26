package com.example.service;

import com.example.dto.TagDTO;
import com.example.entity.TagEntity;
import com.example.exceptions.AppBadRequestException;
import com.example.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public TagDTO create(TagDTO dto) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());





        tagRepository.save(entity);
        ////set security for admin id
        entity.setId(dto.getId());
        return dto;
    }

    public List<TagDTO> getList() {
        Iterable<TagEntity> entities = tagRepository.findAll();

        List<TagDTO> dtos = new ArrayList<>();
        entities.forEach(category -> {
            TagDTO dto = new TagDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setCreatedDate(category.getCreatedDate());
            dtos.add(dto);
        });
        return dtos;
    }

    public String delete(Integer id) {
        Optional<TagEntity> byId = tagRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadRequestException("Categogry Not Found");
        }
        tagRepository.deteleTag(id);
        return "Succcesfully deleted";
    }

    public TagDTO update(Integer id, TagDTO dto) {
        Optional<TagEntity> byId = tagRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadRequestException("Categogry Not Found");
        }
        tagRepository.update(dto.getName(), id);
        dto.setId(id);
        return dto;
    }
}
