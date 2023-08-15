package com.education.onlinecampus.repository.manager;

import com.education.onlinecampus.data.entity.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseChapterRepository extends JpaRepository<CourseChapter,CourseChapter.CourseChapterCompositeKey> {
    List<CourseChapter> findByCourseCourseSeq(Long CourseSeq);
}
