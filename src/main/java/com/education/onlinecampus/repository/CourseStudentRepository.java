package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudent.CourseStudentCompositeKey> {
    Optional<CourseStudent> findByCourseAndStudent(Course course, Member student);
    List<CourseStudent> findByCourse(Course course);
    List<CourseStudent> findByStudent(Member student);
    List<CourseStudent> findByCourseStudentCompositeKey_CourseSeq(Long courseSeq);
    Page<CourseStudent> findByCourseStudentCompositeKey_CourseSeq(Long courseSeq, Pageable pageable);
    List<CourseStudent> findByStudentAndCourse_StatusCode(Member entity, CommonCode code);
}
