package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseChapterRepository extends JpaRepository<CourseChapter, CourseChapter.CourseChapterCompositeKey> {
    List<CourseChapter> findByCourse(Course course);
    CourseChapter findByContent(CourseChapterContent content);
    List<CourseChapter> findBySupplementaryFile(File supplementaryFile);
    Page<CourseChapter> findByCourseChapterCompositeKey_CourseSeq(Long courseSeq, Pageable pageable);
    List<CourseChapter> findByCourseChapterCompositeKey_CourseSeq(Long courseSeq);
    CourseChapter findByCourseAndCourseChapterCompositeKey_ChapterSeq(Course course, Long chapterSeq);
    CourseChapter findByCourse_CourseSeqAndChapterOrder(Long course_courseSeq, Long chapterOrder);
}
