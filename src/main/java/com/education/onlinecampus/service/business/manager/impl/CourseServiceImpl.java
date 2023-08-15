package com.education.onlinecampus.service.business.manager.impl;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.repository.manager.CourseChapterRepository;
import com.education.onlinecampus.repository.manager.CourseRepository;
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
    public void CourseSave(Course course){
        repositoryService.getCourseRepository().save(course);
    }

    @Override
    public void CourseDelete(Course course){ repositoryService.getCourseRepository().delete(course);}

    @Override
    public Course CourseFind(Long CourseSeq){
        Course byId = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return byId;
    }

    @Override
    public void CourseChapterSave(CourseChapter courseChapter){repositoryService.getCourseChapterRepository().save(courseChapter);}

    @Override
    public void CourseChapterDelete(CourseChapter courseChapter){repositoryService.getCourseChapterRepository().delete(courseChapter);}

    @Override
    public List<Course> CourseFindAll(){ return repositoryService.getCourseRepository().findAll();}

    @Override
    public List<CourseChapter> findCourseChapter(Long CourseSeq) {
        return repositoryService.getCourseChapterRepository().findByCourseCourseSeq(CourseSeq);
    }


}
