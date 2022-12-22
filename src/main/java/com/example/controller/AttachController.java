package com.example.controller;

import com.example.dto.AttachDTO;
import com.example.service.AttachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private AttachService attachService;


    /*PUBLIC*/
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam MultipartFile file) {
        log.info("upload + {} "+ file);
        AttachDTO response = attachService.saveToSystem(file);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/load/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] loadImage(@PathVariable String fileName) {
        log.info("load + {} "+ fileName);
        byte[] response = attachService.loadImage(fileName);
        return response;
    }


    @GetMapping(value = "/download/{fileName}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        log.info("download + {} "+ fileName);
        Resource response = attachService.download(fileName);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] openGeneral(@PathVariable String fileName) {
        log.info("open + {} "+ fileName);
        byte[] response = attachService.openGeneral(fileName);
        return response;
    }



    /*ADMIN*/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pagination")
    public ResponseEntity<Page<AttachDTO>> getList(@RequestParam Integer page,
                                     @RequestParam Integer size){
        log.info("pagination + {} ");
        Page<AttachDTO> response= attachService.getList(page, size);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id){
        log.info("delete + {} ");
        Boolean response = attachService.deleteById(id);
        return ResponseEntity.ok(response);
    }

}
