package com.education.onlinecampus.repository.manager;

import com.education.onlinecampus.data.entity.CourseChapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseChapterRepository extends JpaRepository<CourseChapter,CourseChapter.CourseChapterCompositeKey> {
}
