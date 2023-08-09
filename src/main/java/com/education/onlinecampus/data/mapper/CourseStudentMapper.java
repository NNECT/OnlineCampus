package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseStudentDTO;
import com.education.onlinecampus.data.entity.CourseStudent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CourseMapper.class, MemberMapper.class})
public interface CourseStudentMapper {
    CourseStudentMapper INSTANCE = Mappers.getMapper(CourseStudentMapper.class);

    @Mapping(source = "courseStudentDTO", target = "courseStudentCompositeKey", qualifiedByName = "toEntityCourseStudentCompositeKeyMapping")
    @Mapping(source = "courseDTO", target = "course")
    @Mapping(source = "studentDTO", target = "student")
    CourseStudent toEntity(CourseStudentDTO courseStudentDTO);

    @Mapping(source = "course", target = "courseDTO")
    @Mapping(source = "student", target = "studentDTO")
    CourseStudentDTO toDTO(CourseStudent courseStudent);

    @Named("toEntityCourseStudentCompositeKeyMapping")
    default CourseStudent.CourseStudentCompositeKey toEntityCourseStudentCompositeKeyMapping(CourseStudentDTO courseStudentDTO) {
        return new CourseStudent.CourseStudentCompositeKey(
                courseStudentDTO.getCourseDTO().getCourseSeq(),
                courseStudentDTO.getStudentDTO().getMemberSeq()
        );
    }
}
