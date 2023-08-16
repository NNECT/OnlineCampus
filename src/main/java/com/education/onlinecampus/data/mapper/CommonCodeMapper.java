package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.entity.CommonCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonCodeDivisionMapper.class})
public interface CommonCodeMapper {
    CommonCodeMapper INSTANCE = Mappers.getMapper(CommonCodeMapper.class);

    @Mapping(source = "divisionDTO", target = "division")
    CommonCode toEntity(CommonCodeDTO commonCodeDTO);

    @Mapping(source = "division", target = "divisionDTO")
    CommonCodeDTO toDTO(CommonCode commonCode);
}
