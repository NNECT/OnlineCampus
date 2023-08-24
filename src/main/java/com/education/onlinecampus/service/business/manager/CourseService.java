package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;

import java.util.List;

public interface CourseService {
    // Course
    CourseDTO CourseFind(Long CourseSeq);
    List<Course> CourseFindAll();
    List<CourseDTO> courseFindAllByMember(MemberDTO memberDTO);
    void CourseSave(CourseDTO course);
    void CourseDelete(Course course);

    // CourseChapter
    CourseChapterDTO courseChapterFindByCourseAndSeq(CourseDTO courseDTO, Long courseChapterSeq);
    List<List<CourseChapterDTO>> courseChapterFindAllByCourseList(List<CourseDTO> courseDTOList);
    CourseChapterDTO CourseChapterSave(CourseChapterDTO courseChapterDTO);
    void CourseChapterDelete(CourseChapter courseChapter);

    // CourseChapterContent
    CourseChapterContentDTO courseChapterContentFindByVideoId(String videoId);
    CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO);

    // CourseStudent
    CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO);
}
