package com.education.onlinecampus.service.business.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.common.RepositoryService;

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
    List<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq);
    CourseChapterDTO courseChapterFindByCourseAndSeq(CourseDTO courseDTO, Long courseChapterSeq);
    List<CourseChapter> findCourseChapter(Long CourseSeq);
    List<List<CourseChapterDTO>> courseChapterFindAllByCourseList(List<CourseDTO> courseDTOList);
    CourseChapterDTO CourseChapterSave(CourseChapterDTO courseChapterDTO);
    void CourseChapterDelete(CourseChapter courseChapter);

    // CourseChapterContent
    CourseChapterContentDTO courseChapterContentFindByVideoId(String videoId);
    CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO);
    void courseChapterContentDelete(CourseChapterContent courseChapterContent);

    // CourseStudent
    CourseStudentDTO courseStudentFindByCourseAndMember(CourseDTO courseDTO, MemberDTO memberDTO);
    CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO);
    CourseStudentDTO courseStudentFindByCourseAndStudent(CourseDTO courseDTO, MemberDTO memberDTO);
    List<CourseStudent> courseStudentFindByCourseSeq(Long courseSeq);
    List<CourseStudent> CourseStudentAllSave(Long[] memberseqs, Long courseSeq, CourseStudentDTO courseStudentDTO);

    // CourseChapterStudentProgress
    CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO);
    CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudent(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO);
    CourseChapterStudentProgressDTO courseChapterStudentProgressSave(CourseChapterStudentProgressDTO courseChapterStudentProgressDTO);

    List<CourseChapterContent> courseChapterContentFindAll();
}
