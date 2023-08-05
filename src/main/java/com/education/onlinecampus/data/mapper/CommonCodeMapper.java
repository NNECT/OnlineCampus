package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.entity.CommonCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommonCodeMapper {
    CommonCodeMapper INSTANCE = Mappers.getMapper(CommonCodeMapper.class);

    CommonCode toEntity(CommonCodeDTO commonCodeDTO);
    CommonCodeDTO toDTO(CommonCode commonCode);
}
