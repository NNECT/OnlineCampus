package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseChapterContentRepository extends JpaRepository<CourseChapterContent, Long> {
    List<CourseChapterContent> findByThumbnailFile(File thumbnailFile);
    List<CourseChapterContent> findByContentFile(File contentFile);
    List<CourseChapterContent> findByStatusCode(CommonCode statusCode);
}
