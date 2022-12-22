package com.example.service;

import com.example.dto.AttachDTO;
import com.example.entity.AttachEntity;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttachService {

    @Value("${attach.upload.folder}")
    private String attachFolder;
    @Value("${attach.open.url}")
    private String attachOpenUrl;
    @Autowired
    private AttachRepository attachRepository;


    public AttachDTO saveToSystem(MultipartFile file) {
        try {
            // attaches/2022/04/23/UUID.png
            String attachPath = getYmDString(); // 2022/04/23
            String extension = getExtension(file.getOriginalFilename()); // .png....
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "." + extension; // UUID.png

            File folder = new File(attachFolder + attachPath);  // attaches/2022/04/23/
            if (!folder.exists()) {
                folder.mkdirs();
            }

            byte[] bytes = file.getBytes();

            Path path = Paths.get(attachFolder + attachPath + "/" + fileName); // attaches/2022/04/23/UUID.png
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(uuid);
            entity.setPath(attachPath);
            entity.setSize(file.getSize());
            entity.setExtension(extension);
            entity.setCreatedDate(LocalDateTime.now());
            attachRepository.save(entity);

            AttachDTO attachDTO = toDTO(entity);
            attachDTO.setUrl(attachOpenUrl + fileName);
            return attachDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setId(entity.getId());
        attachDTO.setPath(entity.getPath());
        attachDTO.setSize(entity.getSize());
        attachDTO.setCreatedDate(entity.getCreatedDate());
        attachDTO.setType(entity.getType());
        attachDTO.setUrl(entity.getUrl());
        return attachDTO;
    }

    public byte[] loadImage(String fileName) {
        byte[] imageInByte;
        BufferedImage originalImage;
        try {
            AttachEntity attach = attachRepository.findById(fileName).orElseThrow(() -> {
                throw new ItemNotFoundException("Attach not found");
            });

            originalImage = ImageIO.read(new File(attachFolder + attach.getPath() + "/" + fileName + "." + getExtension(fileName)));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public Resource download(String fileName) {
        try {
            AttachEntity entity = get(fileName);
            String path = entity.getPath() + "/" + fileName + "." + entity.getExtension();
            Path file = Paths.get(attachFolder + path);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR: " + e.getMessage());
        }
    }

    public byte[] openGeneral(String fileName) {
        byte[] data;
        try {
            AttachEntity attach = get(fileName);
            Path file = Paths.get(attachFolder+getYmDString() + "/" + fileName + "." + attach.getExtension());
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public Page<AttachDTO> getList(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page-1,size);

        Page<AttachEntity> entityPage = attachRepository.findAll(paging);

        List<AttachEntity> entityList = entityPage.getContent();

        List<AttachDTO> dtoList = new ArrayList<>();

        for (AttachEntity attachEntity : entityList) {
            AttachDTO attachDTO = toDTO(attachEntity);
            dtoList.add(attachDTO);
        }

        Page<AttachDTO> attachDTOPage = new PageImpl<>(dtoList,paging, entityPage.getTotalElements());
        return attachDTOPage;
    }

    public Boolean deleteById(String id) {
        AttachEntity attach = get(id);
        File file = new File(attachFolder + attach.getPath()+"/" + id + "." + attach.getExtension());
        file.delete();

        attachRepository.deleteById(id);
        return true;
    }

    public AttachEntity get(String fileName) {
        return attachRepository.findById(fileName).orElseThrow(() -> {
            throw new ItemNotFoundException("Attach not found");
        });
    }

    public String getYmDString() {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();

        return year + "/" + month + "/" + day; // 2022/04/23
    }
    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

}
