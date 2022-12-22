package com.example.controller;

import com.example.dto.EmailHistoryDTO;
import com.example.service.EmailHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailHistoryController {

    @Autowired
    private EmailHistoryService emailHistoryService;

    @GetMapping("")
    public ResponseEntity<?> getHistoryByEmail(HttpServletRequest request, @RequestParam String email){
        List<EmailHistoryDTO> response = emailHistoryService.getHistoryByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date")
    public ResponseEntity<?> getEmailsByDate(HttpServletRequest request, @RequestParam String date){
        List<EmailHistoryDTO> response = emailHistoryService.getEmailsByDate(date);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getEmailHistoryPagination(HttpServletRequest request, @RequestParam Integer page, @RequestParam Integer size){
        Page<EmailHistoryDTO> response = emailHistoryService.getEmailHistoryPagination(page, size);

        return ResponseEntity.ok(response);
    }
}
