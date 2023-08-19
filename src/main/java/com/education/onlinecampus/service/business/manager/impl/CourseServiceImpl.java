package com.education.onlinecampus.service.business.manager.impl;

import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final RepositoryService repositoryService;

    @Override
    public void CourseSave(CourseDTO course){
        repositoryService.getCourseRepository().save(course.toEntity());
    }

    @Override
    public void CourseDelete(Course course){ repositoryService.getCourseRepository().delete(course);}

    @Override
    public Course CourseFind(Long CourseSeq){
        Course byId = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return byId;
    }
    @Override
    public void CourseChapterSave(CourseChapterDTO courseChapter){
        repositoryService.getCourseChapterRepository().save(courseChapter.toEntity());
    }

    @Override
    public void CourseChapterDelete(CourseChapter courseChapter){repositoryService.getCourseChapterRepository().delete(courseChapter);}

    @Override
    public List<Course> CourseFindAll(){ return repositoryService.getCourseRepository().findAll();}

    @Override
    public List<CourseChapter> findCourseChapter(Long CourseSeq) {
        Course course = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return repositoryService.getCourseChapterRepository().findByCourse(course);
    }

    @Override
    public List<CourseChapter> CourseChapterFindAll(){
        List<CourseChapter> all = repositoryService.getCourseChapterRepository().findAll();
        return all;
    }

    
}
