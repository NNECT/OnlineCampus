package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudent.CourseStudentCompositeKey> {
    List<CourseStudent> findByCourse(Course course);
    List<CourseStudent> findByStudent(Member student);
    List<CourseStudent> findByCourseStudentCompositeKey_CourseSeq(Long courseSeq);
    CourseStudent findByCourseStudentCompositeKey_CourseSeqAndCourseStudentCompositeKey_StudentSeq(Long courseSeq,Long studentSeq);


}
