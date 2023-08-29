package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.FileDTO;
import com.education.onlinecampus.repository.FileRepository;
import com.education.onlinecampus.service.common.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.education.onlinecampus.config.Constant.IMAGE_PATH_CONTENT;
import static com.education.onlinecampus.config.Constant.IMAGE_PATH_MEMBER;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileRepository fileRepository;

    @Override
    public FileDTO saveProfileImage(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = generateUniqueFileName(originalFilename);

        String imageUrl = IMAGE_PATH_MEMBER + fileName; // 이미지 파일명으로 변경
        Path saveImagePath = Paths.get(imageUrl);

        File directory = new File(IMAGE_PATH_MEMBER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File destinationFile = new File(saveImagePath.toUri());

        multipartFile.transferTo(destinationFile);

        // FileDTO 객체 생성 및 값 설정
        FileDTO fileDTO = new FileDTO();
        fileDTO.setOriginalFileName(originalFilename);
        fileDTO.setFileName(fileName);
        fileDTO.setFileSize((int) multipartFile.getSize());

        return fileDTO;
    }

    private String generateUniqueFileName(String originalFilename) {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        return timeStamp + "_" + originalFilename;
    }
    @Override
    public FileDTO filesave(FileDTO fileDTO){
        FileDTO fileDTO1 = fileRepository.save(fileDTO.toEntity()).toDTO();
        return fileDTO1;
    }

    @Override
    public FileDTO saveContentImage(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = generateUniqueFileName(originalFilename);

        String imageUrl = IMAGE_PATH_CONTENT + fileName; // 이미지 파일명으로 변경
        Path saveImagePath = Paths.get(imageUrl);

        File directory = new File(IMAGE_PATH_CONTENT);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File destinationFile = new File(saveImagePath.toUri());

        multipartFile.transferTo(destinationFile);

        // FileDTO 객체 생성 및 값 설정
        FileDTO fileDTO = new FileDTO();
        fileDTO.setOriginalFileName(originalFilename);
        fileDTO.setFileName(fileName);
        fileDTO.setFileSize((int) multipartFile.getSize());

        return fileDTO;
    }
}
