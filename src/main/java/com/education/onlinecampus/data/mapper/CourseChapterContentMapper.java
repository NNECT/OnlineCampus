package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {FileMapper.class, CommonCodeMapper.class})
public interface CourseChapterContentMapper {
    CourseChapterContentMapper INSTANCE = Mappers.getMapper(CourseChapterContentMapper.class);

    @Mapping(source = "thumbnailFileDTO", target = "thumbnailFile")
    CourseChapterContent toEntity(CourseChapterContentDTO courseChapterContentDTO);

    @Mapping(source = "thumbnailFile", target = "thumbnailFileDTO")
    CourseChapterContentDTO toDTO(CourseChapterContent courseChapterContent);
}
