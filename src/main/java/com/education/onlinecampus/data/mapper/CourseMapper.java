package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.entity.Course;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "statusCode", target = "statusCode")
    Course toEntity(CourseDTO courseDTO);

    @Mapping(source = "statusCode", target = "statusCode")
    CourseDTO toDTO(Course course);
}
