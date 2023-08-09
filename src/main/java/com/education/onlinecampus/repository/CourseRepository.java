package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseSeq(Long courseSeq);
}
