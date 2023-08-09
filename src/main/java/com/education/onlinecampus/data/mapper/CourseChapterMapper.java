package com.education.onlinecampus.data.mapper;

import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.entity.CourseChapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CourseMapper.class, FileMapper.class, CourseChapterContentMapper.class})
public interface CourseChapterMapper {
    CourseChapterMapper INSTANCE = Mappers.getMapper(CourseChapterMapper.class);

    @Mapping(source = "courseChapterDTO", target = "courseChapterCompositeKey", qualifiedByName = "toEntityCourseChapterCompositeKeyMapping")
    @Mapping(source = "courseDTO", target = "course")
    @Mapping(source = "contentDTO", target = "content")
    @Mapping(source = "supplementaryFileDTO", target = "supplementaryFile")
    CourseChapter toEntity(CourseChapterDTO courseChapterDTO);

    @Mapping(source = "course", target = "courseDTO")
    @Mapping(source = "courseChapterCompositeKey.chapterSeq", target = "chapterSeq")
    @Mapping(source = "content", target = "contentDTO")
    @Mapping(source = "supplementaryFile", target = "supplementaryFileDTO")
    CourseChapterDTO toDTO(CourseChapter courseChapter);

    @Named("toEntityCourseChapterCompositeKeyMapping")
    default CourseChapter.CourseChapterCompositeKey toEntityCourseChapterCompositeKeyMapping(CourseChapterDTO courseChapterDTO) {
        return new CourseChapter.CourseChapterCompositeKey(
                courseChapterDTO.getCourseDTO().getCourseSeq(),
                courseChapterDTO.getChapterSeq()
        );
    }
}
