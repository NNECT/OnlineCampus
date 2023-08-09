package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudent.CourseStudentCompositeKey> {
}
