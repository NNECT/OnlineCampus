package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.FileDTO;
import com.education.onlinecampus.data.entity.File;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    File toEntity(FileDTO fileDTO);
    FileDTO toDTO(File file);
}
