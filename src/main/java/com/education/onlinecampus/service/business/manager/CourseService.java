package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.CourseStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    // Course
    CourseDTO CourseFind(Long CourseSeq);
    List<Course> CourseFindAll();
    List<CourseDTO> courseFindAllByMember(MemberDTO memberDTO);
    List<Course> findByCourseNameContaining(String searchKeyword, String searchKeyword1);
    Course CourseSave(CourseDTO course);
    void CourseDelete(Course course);

    // CourseChapter
    CourseChapter findByCourseAndChapterOrder(Long courseSeq, Long chapterorder);
    List<CourseChapter> CourseChapterFindAll();
    Page<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq, Pageable pageable);
    CourseChapterDTO courseChapterFindByCourseAndSeq(CourseDTO courseDTO, Long courseChapterSeq);
    List<CourseChapter> findCourseChapter(Long CourseSeq);
    Page<CourseChapter> findCourseChapter(Long CourseSeq,Pageable pageable);
    List<CourseChapterDTO> courseChapterFindByCourse(CourseDTO courseDTO);
    List<List<CourseChapterDTO>> courseChapterFindAllByCourseList(List<CourseDTO> courseDTOList);
    CourseChapterDTO CourseChapterSave(CourseChapterDTO courseChapterDTO);
    void CourseChapterDelete(CourseChapter courseChapter);

    // CourseChapterContent
    CourseChapterContentDTO courseChapterContentFindByVideoId(String videoId);

    List<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq);

    CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO);

    // CourseStudent
    CourseStudentDTO courseStudentFindByCourseAndMember(CourseDTO courseDTO, MemberDTO memberDTO);
    CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO);
    CourseStudentDTO courseStudentFindByCourseAndStudent(CourseDTO courseDTO, MemberDTO memberDTO);
    List<CourseStudent> courseStudentFindByCourseSeq(Long courseSeq);

    void deleteByCourse_courseSeqAndStudent_memberSeq(Long courseSeq, Long memberSeq);

    Page<CourseStudent> courseStudentFindByCourseSeq(Long courseSeq,Pageable pageable);
    List<CourseStudent> CourseStudentAllSave(Long[] memberseqs, Long courseSeq, CourseStudentDTO courseStudentDTO);

    // CourseChapterStudentProgress
    CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO);
    CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudent(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO);
    CourseChapterStudentProgressDTO courseChapterStudentProgressSave(CourseChapterStudentProgressDTO courseChapterStudentProgressDTO);

    List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressFindAllByEachCourse(MemberDTO student, List<CourseDTO> courseList);
    List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressFindAllByEachCourse(MemberDTO student, List<CourseDTO> courseList, List<List<CourseChapterDTO>> chapterList);

    double getMemeberProgress(MemberDTO student);
    double getMemeberProgress(List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressList);
    double getCourseProgress(MemberDTO studentDTO, CourseDTO courseDTO);
    double getChapterProgress(MemberDTO studentDTO, CourseChapterDTO courseChapterDTO);

    List<CourseChapterContent> courseChapterContentFindAll();

    Page<Course> courseFindAllPage(Pageable pageable);

    Page<CourseChapter> courseChapterFindAllpage(Pageable pageable);
    Page<CourseStudent> courseStudentFindAllpage(Pageable pageable);

    Page<CourseStudent> courseStudentFindByCourse_courseSeq(Long courseSeq,Pageable pageable);
}
