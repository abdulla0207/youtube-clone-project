package com.example.service;

import com.example.dto.EmailHistoryDTO;
import com.example.entity.EmailHistoryEntity;
import com.example.repository.EmailHistoryRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private EmailHistoryDTO toDTO(EmailHistoryEntity emailHistoryEntity) {
        EmailHistoryDTO emailDTO = new EmailHistoryDTO();
        emailDTO.setEmail(emailHistoryEntity.getEmail());
        emailDTO.setMessage(emailHistoryEntity.getMessage());
        emailDTO.setTitle(emailHistoryEntity.getTitle());
        emailDTO.setCreatedDate(emailHistoryEntity.getCreatedDate());
        emailDTO.setId(emailHistoryEntity.getId());
        return emailDTO;
    }

    public List<EmailHistoryDTO> getEmailsByDate(String date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, timeFormatter);

        List<EmailHistoryEntity> emailHistoryEntityList = emailHistoryRepository.getEmailByDate(localDate);

        List<EmailHistoryDTO> dtoList = new ArrayList<>();

        emailHistoryEntityList.forEach(emailHistoryEntity -> {
            EmailHistoryDTO emailHistoryDTO = toDTO(emailHistoryEntity);
            dtoList.add(emailHistoryDTO);
        });

        return dtoList;
    }

    public Page<EmailHistoryDTO> getEmailHistoryPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmailHistoryEntity> emailHistoryEntityPage = emailHistoryRepository.findAll(pageable);

        List<EmailHistoryEntity> emailHistoryEntityList = emailHistoryEntityPage.getContent();
        List<EmailHistoryDTO> dtoList = new ArrayList<>();

        emailHistoryEntityList.forEach(emailHistoryEntity -> {
            EmailHistoryDTO emailHistoryDTO = toDTO(emailHistoryEntity);
            dtoList.add(emailHistoryDTO);
        });

        return new PageImpl<>(dtoList, pageable, emailHistoryEntityPage.getTotalElements());
    }
}
