package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseChapterRepository extends JpaRepository<CourseChapter, CourseChapter.CourseChapterCompositeKey> {
    List<CourseChapter> findByCourse(Course course);
    CourseChapter findByContent(CourseChapterContent content);
    List<CourseChapter> findBySupplementaryFile(File supplementaryFile);
    List<CourseChapter> findByCourseChapterCompositeKey_CourseSeq(Long courseSeq);
    CourseChapter findByCourse_CourseSeqAndChapterOrder(Long courseSeq,Integer chapterOrder);
    @Query("SELECT COALESCE(MAX(cc.courseChapterCompositeKey.chapterSeq), 0) FROM CourseChapter cc WHERE cc.courseChapterCompositeKey.courseSeq = :courseSeq")
    Long findMaxChapterSeqByCourseSeq(Long courseSeq);
}
