package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseChapterRepository extends JpaRepository<CourseChapter, CourseChapter.CourseChapterCompositeKey> {
    List<CourseChapter> findByCourseCourseSeq(Long CourseSeq);
    List<CourseChapter> findByCourse(Course course);
    CourseChapter findByContent(CourseChapterContent content);
    List<CourseChapter> findBySupplementaryFile(File supplementaryFile);
}
