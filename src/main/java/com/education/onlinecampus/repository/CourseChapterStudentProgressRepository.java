package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CourseChapterStudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseChapterStudentProgressRepository extends JpaRepository<CourseChapterStudentProgress, CourseChapterStudentProgress.CourseChapterStudentProgressCompositeKey> {
}
