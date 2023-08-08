package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;

public interface CourseService {
    void CourseSave(Course course);

    void CourseDelete(Course course);

    Course CourseFind(Long CourseSeq);

    void CourseChapterSave(CourseChapter courseChapter);

    void CourseChapterDelete(CourseChapter courseChapter);
}
