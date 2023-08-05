package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "pictureFile", target = "pictureFile")
    Member toEntity(MemberDTO memberDTO);
    @Mapping(source = "pictureFile", target = "pictureFile")
    MemberDTO toDTO(Member member);
}
