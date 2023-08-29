package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseSeq(Long courseSeq);
    List<Course> findByStatusCode(CommonCode statusCode);
    List<Course> findByCourseNameOrCourseNameContaining(String searchKeyword, String searchKeyword1);
    Page<Course> findAll(Pageable pageable);

}
