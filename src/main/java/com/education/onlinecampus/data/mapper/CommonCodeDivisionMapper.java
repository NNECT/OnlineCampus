package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CommonCodeDivisionDTO;
import com.education.onlinecampus.data.entity.CommonCodeDivision;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommonCodeDivisionMapper {
    CommonCodeDivisionMapper INSTANCE = Mappers.getMapper(CommonCodeDivisionMapper.class);

    CommonCodeDivision toEntity(CommonCodeDivisionDTO commonCodeDivisionDTO);
    CommonCodeDivisionDTO toDTO(CommonCodeDivision commonCodeDivision);
}
