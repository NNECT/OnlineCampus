package com.education.onlinecampus.service.business.manager.impl;

import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.dto.CourseStudentDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final RepositoryService repositoryService;

    @Override
    public Course CourseSave(CourseDTO course){
        return repositoryService.getCourseRepository().save(course.toEntity());
    }

    @Override
    public RepositoryService repositoryService(){
        return repositoryService;
    }

    @Override
    public void CourseDelete(Course course){ repositoryService.getCourseRepository().delete(course);}

    @Override
    public Course CourseFind(Long CourseSeq){
        Course byId = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return byId;
    }
    @Override
    public CourseChapter CourseChapterSave(CourseChapterDTO courseChapter){
        return repositoryService.getCourseChapterRepository().save(courseChapter.toEntity());
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
        List<CourseChapter> all = repositoryService.getCourseChapterRepository().findAll(Sort.by(Sort.Order.asc("chapterOrder")));
        return all;
    }
    @Override
    public void CourseStudentAllSave(Long[] memberseqs, Long courseSeq, CourseStudentDTO courseStudentDTO){
        for(int i=0;i<memberseqs.length;i++){
            Member byId = repositoryService.getMemberRepository().findById(memberseqs[i]).orElseThrow();
            Course byId1 = repositoryService.getCourseRepository().findById(courseSeq).orElseThrow();
            courseStudentDTO.setStudentDTO(byId.toDTO());
            courseStudentDTO.setCourseDTO(byId1.toDTO());
            repositoryService.getCourseStudentRepository().save(courseStudentDTO.toEntity());
        }

    }
    @Override
    public CourseChapter findByCourseAndChapterOrder(Long courseSeq, Integer chapterorder){
        return repositoryService.getCourseChapterRepository().findByCourse_CourseSeqAndChapterOrder(courseSeq,chapterorder);
    }

    @Override
    public List<Course> findByCourseNameContaining(String searchKeyword, String searchKeyword1){
        return repositoryService.getCourseRepository().findByCourseNameOrCourseNameContaining(searchKeyword,searchKeyword1);
    }

    @Override
    public List<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq){
        return repositoryService.getCourseChapterRepository().findByCourseChapterCompositeKey_CourseSeq(courseSeq);
    }
}
