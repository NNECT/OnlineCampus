package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.dto.CourseStudentDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.common.RepositoryService;

import java.util.List;

public interface CourseService {
    Course CourseSave(CourseDTO course);

    RepositoryService repositoryService();

    void CourseDelete(Course course);

    Course CourseFind(Long CourseSeq);

    List<Course> CourseFindAll();

    CourseChapter CourseChapterSave(CourseChapterDTO courseChapter);

    void CourseChapterDelete(CourseChapter courseChapter);

    List<CourseChapter> findCourseChapter(Long CourseSeq);

    List<CourseChapter> CourseChapterFindAll();

    List<CourseStudent> CourseStudentAllSave(Long[] memberseqs, Long courseSeq, CourseStudentDTO courseStudentDTO);

    CourseChapter findByCourseAndChapterOrder(Long courseSeq, Integer chapterorder);

    List<Course> findByCourseNameContaining(String searchKeyword, String searchKeyword1);

    List<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq);
}
