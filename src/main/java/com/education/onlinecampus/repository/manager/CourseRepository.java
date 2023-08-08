package com.education.onlinecampus.repository.manager;

import com.education.onlinecampus.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findByCourseSeq(Long courseSeq);
}
