package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {FileMapper.class, CommonCodeMapper.class})
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "memberDivisionDTO", target = "memberDivision")
    @Mapping(source = "genderCodeDTO", target = "genderCode")
    @Mapping(source = "pictureFileDTO", target = "pictureFile")
    Member toEntity(MemberDTO memberDTO);
    @Mapping(source = "memberDivision", target = "memberDivisionDTO")
    @Mapping(source = "genderCode", target = "genderCodeDTO")
    @Mapping(source = "pictureFile", target = "pictureFileDTO")
    MemberDTO toDTO(Member member);
}
