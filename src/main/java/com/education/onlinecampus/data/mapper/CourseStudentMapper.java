package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseStudentDTO;
import com.education.onlinecampus.data.entity.CourseStudent;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CourseStudentMapper {
    CourseStudentMapper INSTANCE = Mappers.getMapper(CourseStudentMapper.class);

    @Mapping(source = "course.courseSeq", target = "courseStudentCompositeKey.courseSeq")
    @Mapping(source = "student.memberSeq", target = "courseStudentCompositeKey.studentSeq")
    @Mapping(source = "course", target = "course")
    @Mapping(source = "student", target = "student")
    CourseStudent toEntity(CourseStudentDTO courseStudentDTO);

    @Mapping(source = "courseStudentCompositeKey.courseSeq", target = "course.courseSeq")
    @Mapping(source = "courseStudentCompositeKey.studentSeq", target = "student.memberSeq")
    @Mapping(source = "course", target = "course")
    @Mapping(source = "student", target = "student")
    CourseStudentDTO toDTO(CourseStudent courseStudent);
}
