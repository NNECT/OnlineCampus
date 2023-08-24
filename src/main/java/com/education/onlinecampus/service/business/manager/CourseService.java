package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;

import java.util.List;

public interface CourseService {
    void CourseSave(CourseDTO course);

    void CourseDelete(Course course);

    CourseDTO CourseFind(Long CourseSeq);

    List<Course> CourseFindAll();

    CourseChapterDTO CourseChapterSave(CourseChapterDTO courseChapterDTO);

    void CourseChapterDelete(CourseChapter courseChapter);

    List<CourseDTO> courseFindAllByMember(MemberDTO memberDTO);

    List<List<CourseChapterDTO>> courseChapterFindAllByCourseList(List<CourseDTO> courseDTOList);

    CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO);

    CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO);
}
