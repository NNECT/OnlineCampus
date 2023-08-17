package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;

import java.util.List;

public interface CourseService {
    void CourseSave(CourseDTO course);

    void CourseDelete(Course course);

    Course CourseFind(Long CourseSeq);

    List<Course> CourseFindAll();

    void CourseChapterSave(CourseChapter courseChapter);

    void CourseChapterDelete(CourseChapter courseChapter);

    List<CourseChapter> findCourseChapter(Long CourseSeq);
}
