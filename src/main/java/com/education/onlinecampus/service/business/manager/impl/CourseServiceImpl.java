package com.education.onlinecampus.service.business.manager.impl;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final RepositoryService repositoryService;

    @Override
    public void CourseSave(CourseDTO course){
        repositoryService.getCourseRepository().save(course.toEntity());
    }

    @Override
    public void CourseDelete(Course course){ repositoryService.getCourseRepository().delete(course);}

    @Override
    public CourseDTO CourseFind(Long CourseSeq){
        return repositoryService.getCourseRepository().findByCourseSeq(CourseSeq).toDTO();
    }
    @Override
    public CourseChapterDTO CourseChapterSave(CourseChapterDTO courseChapterDTO){
        return repositoryService.getCourseChapterRepository().save(courseChapterDTO.toEntity()).toDTO();
    }

    @Override
    public void CourseChapterDelete(CourseChapter courseChapter){repositoryService.getCourseChapterRepository().delete(courseChapter);}

    @Override
    public List<Course> CourseFindAll(){ return repositoryService.getCourseRepository().findAll();}

    @Override
    public List<CourseDTO> courseFindAllByMember(MemberDTO memberDTO) {
        CommonCode code = repositoryService.getCommonCodeRepository().findById("C002").orElseThrow();
        List<CourseStudent> courseStudentList = repositoryService.getCourseStudentRepository().findByStudentAndCourse_StatusCode(memberDTO.toEntity(), code);
        return courseStudentList.stream().map(courseStudent -> courseStudent.getCourse().toDTO()).collect(Collectors.toList());
    }

    @Override
    public List<List<CourseChapterDTO>> courseChapterFindAllByCourseList(List<CourseDTO> courseDTOList) {
        List<List<CourseChapterDTO>> resultList = new ArrayList<>();
        for (CourseDTO courseDTO : courseDTOList) {
            List<CourseChapterDTO> courseChapterDTOList = repositoryService.getCourseChapterRepository().findByCourse(courseDTO.toEntity()).stream().map(CourseChapter::toDTO).collect(Collectors.toList());
            resultList.add(courseChapterDTOList);
        }
        return resultList;
    }

    @Override
    public CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO) {
        return repositoryService.getCourseChapterContentRepository().save(courseChapterContentDTO.toEntity()).toDTO();
    }

    @Override
    public CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO) {
        return repositoryService.getCourseStudentRepository().save(courseStudentDTO.toEntity()).toDTO();
    }
}
