package com.example.service;

import com.example.dto.EmailHistoryDTO;
import com.example.entity.EmailHistoryEntity;
import com.example.repository.EmailHistoryRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailMime(String fromAccount, String toAccount, String subject, String content){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setFrom(fromAccount);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(content);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);

            EmailHistoryEntity emailHistoryEntity = new EmailHistoryEntity();
            emailHistoryEntity.setEmail(toAccount);
            emailHistoryEntity.setMessage(content);
            emailHistoryEntity.setCreatedDate(LocalDateTime.now());
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }

    public List<EmailHistoryDTO> getHistoryByEmail(String email) {
        List<EmailHistoryDTO> emailHistoryDTOList = new ArrayList<>();

        List<EmailHistoryEntity> emailHistoryEntityList = emailHistoryRepository.findAllByEmail(email);

        emailHistoryEntityList.forEach(emailHistoryEntity -> {
            EmailHistoryDTO emailHistoryDTO = toDTO(emailHistoryEntity);
            emailHistoryDTOList.add(emailHistoryDTO);
        });

        return emailHistoryDTOList;
    }

    public Page<EmailHistoryDTO> getEmailsByDate(String date, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, timeFormatter);

        Page<EmailHistoryEntity> emailHistoryEntityPage = emailHistoryRepository.getEmailByDate(localDate, pageable);
        List<EmailHistoryEntity> emailHistoryEntityList = emailHistoryEntityPage.getContent();

        List<EmailHistoryDTO> dtoList = new ArrayList<>();

        emailHistoryEntityList.forEach(emailHistoryEntity -> {
            EmailHistoryDTO emailHistoryDTO = toDTO(emailHistoryEntity);
            dtoList.add(emailHistoryDTO);
        });

        return new PageImpl<>(dtoList, pageable, emailHistoryEntityPage.getTotalElements());
    }

    public Page<EmailHistoryDTO> getEmailHistoryPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return getEmailHistoryDTOS(pageable);
    }

    public Page<EmailHistoryDTO> getEmailHistoryPaginationOrderByEmailAndDate(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("email").and(Sort.by("created_date")));

        return getEmailHistoryDTOS(pageable);
    }

    private Page<EmailHistoryDTO> getEmailHistoryDTOS(Pageable pageable) {
        Page<EmailHistoryEntity> emailHistoryEntityPage = emailHistoryRepository.findAll(pageable);

        List<EmailHistoryEntity> emailHistoryEntityList = emailHistoryEntityPage.getContent();
        List<EmailHistoryDTO> dtoList = new ArrayList<>();

        emailHistoryEntityList.forEach(emailHistoryEntity -> {
            EmailHistoryDTO emailHistoryDTO = toDTO(emailHistoryEntity);
            dtoList.add(emailHistoryDTO);
        });

        return new PageImpl<>(dtoList, pageable, emailHistoryEntityPage.getTotalElements());
    }

    private EmailHistoryDTO toDTO(EmailHistoryEntity emailHistoryEntity) {
        EmailHistoryDTO emailDTO = new EmailHistoryDTO();
        emailDTO.setEmail(emailHistoryEntity.getEmail());
        emailDTO.setMessage(emailHistoryEntity.getMessage());
        emailDTO.setTitle(emailHistoryEntity.getTitle());
        emailDTO.setCreatedDate(emailHistoryEntity.getCreatedDate());
        emailDTO.setId(emailHistoryEntity.getId());
        return emailDTO;
    }
}
