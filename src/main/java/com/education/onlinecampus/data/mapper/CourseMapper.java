package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CommonCodeMapper.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "statusCodeDTO", target = "statusCode")
    Course toEntity(CourseDTO courseDTO);

    @Mapping(source = "statusCode", target = "statusCodeDTO")
    CourseDTO toDTO(Course course);
}
