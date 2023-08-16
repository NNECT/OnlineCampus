package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterStudentProgress;
import com.education.onlinecampus.data.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseChapterStudentProgressRepository extends JpaRepository<CourseChapterStudentProgress, CourseChapterStudentProgress.CourseChapterStudentProgressCompositeKey> {
    List<CourseChapterStudentProgress> findByCourse(Course course);
    List<CourseChapterStudentProgress> findByChapter(CourseChapter chapter);
    List<CourseChapterStudentProgress> findByStudent(CourseStudent student);
}
