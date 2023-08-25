package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseChapterStudentProgressDTO;
import com.education.onlinecampus.data.entity.CourseChapterStudentProgress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CourseMapper.class, CourseChapterMapper.class, CourseStudentMapper.class})
public interface CourseChapterStudentProgressMapper {
    CourseChapterStudentProgressMapper INSTANCE = Mappers.getMapper(CourseChapterStudentProgressMapper.class);

    default CourseChapterStudentProgress toEntity(CourseChapterStudentProgressDTO courseChapterStudentProgressDTO) {
        return new CourseChapterStudentProgress(
                new CourseChapterStudentProgress.CourseChapterStudentProgressCompositeKey(
                        courseChapterStudentProgressDTO.getCourseDTO().getCourseSeq(),
                        courseChapterStudentProgressDTO.getChapterDTO().getChapterSeq(),
                        courseChapterStudentProgressDTO.getStudentDTO().getStudentDTO().getMemberSeq()
                ),
                CourseMapper.INSTANCE.toEntity(courseChapterStudentProgressDTO.getCourseDTO()),
                CourseChapterMapper.INSTANCE.toEntity(courseChapterStudentProgressDTO.getChapterDTO()),
                CourseStudentMapper.INSTANCE.toEntity(courseChapterStudentProgressDTO.getStudentDTO()),
                courseChapterStudentProgressDTO.getFinalPosition(),
                courseChapterStudentProgressDTO.getMaxPosition(),
                courseChapterStudentProgressDTO.getCompleted()
        );
    };

    default CourseChapterStudentProgressDTO toDTO(CourseChapterStudentProgress courseChapterStudentProgress) {
        return new CourseChapterStudentProgressDTO(
                CourseMapper.INSTANCE.toDTO(courseChapterStudentProgress.getCourse()),
                CourseChapterMapper.INSTANCE.toDTO(courseChapterStudentProgress.getChapter()),
                CourseStudentMapper.INSTANCE.toDTO(courseChapterStudentProgress.getStudent()),
                courseChapterStudentProgress.getFinalPosition(),
                courseChapterStudentProgress.getMaxPosition(),
                courseChapterStudentProgress.getCompleted()
        );
    };
}
