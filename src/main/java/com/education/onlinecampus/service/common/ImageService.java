package com.education.onlinecampus.service.common;

import com.education.onlinecampus.data.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    FileDTO saveProfileImage(MultipartFile multipartFile) throws IOException;

    FileDTO filesave(FileDTO fileDTO);

    FileDTO saveContentImage(MultipartFile multipartFile) throws IOException;
}
