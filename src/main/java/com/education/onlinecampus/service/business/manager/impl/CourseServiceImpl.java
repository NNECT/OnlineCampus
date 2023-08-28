package com.education.onlinecampus.service.business.manager.impl;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Course CourseSave(CourseDTO course){
        return repositoryService.getCourseRepository().save(course.toEntity());
    }

    @Override
    public void CourseDelete(Course course){ repositoryService.getCourseRepository().delete(course);}

    @Override
    public CourseChapterDTO courseChapterFindByCourseAndSeq(CourseDTO courseDTO, Long courseChapterSeq) {
        Course course = courseDTO.toEntity();
        return repositoryService.getCourseChapterRepository().findByCourseAndCourseChapterCompositeKey_ChapterSeq(course, courseChapterSeq).toDTO();
    }

    @Override
    public List<CourseChapter> findCourseChapter(Long CourseSeq) {
        Course course = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return repositoryService.getCourseChapterRepository().findByCourse(course);
    }

    @Override
    public Page<CourseChapter> findCourseChapter(Long CourseSeq,Pageable pageable) {
        Course course = repositoryService.getCourseRepository().findByCourseSeq(CourseSeq);
        return repositoryService.getCourseChapterRepository().findByCourse(course,pageable);
    }

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
    public CourseChapterContentDTO courseChapterContentFindByVideoId(String videoId) {
        return null;
    }

    @Override
    public List<Course> CourseFindAll(){ return repositoryService.getCourseRepository().findAll();}

    @Override
    public List<CourseDTO> courseFindAllByMember(MemberDTO memberDTO) {
        CommonCode code = repositoryService.getCommonCodeRepository().findById("C002").orElseThrow();
        List<CourseStudent> courseStudentList = repositoryService.getCourseStudentRepository().findByStudentAndCourse_StatusCode(memberDTO.toEntity(), code);
        return courseStudentList.stream().map(courseStudent -> courseStudent.getCourse().toDTO()).collect(Collectors.toList());
    }

    @Override
    public List<CourseChapter> CourseChapterFindAll(){
        List<CourseChapter> all = repositoryService.getCourseChapterRepository().findAll(Sort.by(Sort.Order.asc("chapterOrder")));
        return all;
    }

    @Override
    public List<CourseChapterDTO> courseChapterFindByCourse(CourseDTO courseDTO) {
        Course course = courseDTO.toEntity();
        return repositoryService.getCourseChapterRepository().findByCourse(course).stream().map(CourseChapter::toDTO).collect(Collectors.toList());
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
    public List<CourseStudent> CourseStudentAllSave(Long[] memberseqs, Long courseSeq, CourseStudentDTO courseStudentDTO){
        List<CourseStudent> savedCourseStudents = new ArrayList<>();
        for(int i=0;i<memberseqs.length;i++){
            Member byId = repositoryService.getMemberRepository().findById(memberseqs[i]).orElseThrow();
            Course byId1 = repositoryService.getCourseRepository().findById(courseSeq).orElseThrow();
            courseStudentDTO.setStudentDTO(byId.toDTO());
            courseStudentDTO.setCourseDTO(byId1.toDTO());
            savedCourseStudents.add(repositoryService.getCourseStudentRepository().save(courseStudentDTO.toEntity()));
        }
            return savedCourseStudents;
    }

    @Override
    public CourseChapter findByCourseAndChapterOrder(Long courseSeq, Long chapterorder){
        return repositoryService.getCourseChapterRepository().findByCourse_CourseSeqAndChapterOrder(courseSeq, chapterorder);
    }

    @Override
    public List<Course> findByCourseNameContaining(String searchKeyword, String searchKeyword1){
        return repositoryService.getCourseRepository().findByCourseNameOrCourseNameContaining(searchKeyword,searchKeyword1);
    }

    @Override
    public Page<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq,Pageable pageable){
        return repositoryService.getCourseChapterRepository().findByCourseChapterCompositeKey_CourseSeq(courseSeq,pageable);
    }

    @Override
    public List<CourseChapter> findByCourseChapterCompositeKeyCourseSeq(Long courseSeq){
        return repositoryService.getCourseChapterRepository().findByCourseChapterCompositeKey_CourseSeq(courseSeq);
    }

    @Override
    public CourseChapterContentDTO courseChapterContentSave(CourseChapterContentDTO courseChapterContentDTO) {
        return repositoryService.getCourseChapterContentRepository().save(courseChapterContentDTO.toEntity()).toDTO();
    }

    @Override
    public CourseStudentDTO courseStudentFindByCourseAndMember(CourseDTO courseDTO, MemberDTO memberDTO) {
        CourseStudent courseStudent = repositoryService.getCourseStudentRepository()
                .findByCourseAndStudent(courseDTO.toEntity(), memberDTO.toEntity())
                .orElse(null);
        if (courseStudent == null) return null;
        return courseStudent.toDTO();
    }

    @Override
    public CourseStudentDTO courseStudentSave(CourseStudentDTO courseStudentDTO) {
        return repositoryService.getCourseStudentRepository().save(courseStudentDTO.toEntity()).toDTO();
    }

    @Override
    public CourseStudentDTO courseStudentFindByCourseAndStudent(CourseDTO courseDTO, MemberDTO memberDTO) {
        return repositoryService.getCourseStudentRepository().findByCourseAndStudent(courseDTO.toEntity(), memberDTO.toEntity()).orElseThrow().toDTO();
    }

    @Override
    public List<CourseStudent> courseStudentFindByCourseSeq(Long courseSeq) {
        return repositoryService.getCourseStudentRepository().findByCourseStudentCompositeKey_CourseSeq(courseSeq);
    }

    @Override
    public void deleteByCourse_courseSeqAndStudent_memberSeq(Long courseSeq,Long memberSeq) {
        repositoryService.getCourseStudentRepository().deleteByCourse_courseSeqAndStudent_memberSeq(courseSeq,memberSeq);
    }

    @Override
    public Page<CourseStudent> courseStudentFindByCourseSeq(Long courseSeq,Pageable pageable) {
        return repositoryService.getCourseStudentRepository().findByCourseStudentCompositeKey_CourseSeq(courseSeq,pageable);
    }

    @Override
    public CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO) {
        CourseChapterStudentProgressDTO courseChapterStudentProgress = courseChapterStudentProgressFindByChapterAndStudent(courseChapterDTO, courseStudentDTO);
        if (courseChapterStudentProgress == null) {
            courseChapterStudentProgress = CourseChapterStudentProgressDTO.builder()
                    .courseDTO(courseChapterDTO.getCourseDTO())
                    .chapterDTO(courseChapterDTO)
                    .studentDTO(courseStudentDTO)
                    .build();
            courseChapterStudentProgress = repositoryService.getCourseChapterStudentProgressRepository().save(courseChapterStudentProgress.toEntity()).toDTO();
        }
        return courseChapterStudentProgress;
    }

    @Override
    public CourseChapterStudentProgressDTO courseChapterStudentProgressFindByChapterAndStudent(CourseChapterDTO courseChapterDTO, CourseStudentDTO courseStudentDTO) {
        CourseChapterStudentProgress courseChapterStudentProgress = repositoryService.getCourseChapterStudentProgressRepository()
                .findByChapterAndStudent(courseChapterDTO.toEntity(), courseStudentDTO.toEntity())
                .orElse(null);
        if (courseChapterStudentProgress == null) return null;
        return courseChapterStudentProgress.toDTO();
    }

    @Override
    public CourseChapterStudentProgressDTO courseChapterStudentProgressSave(CourseChapterStudentProgressDTO courseChapterStudentProgressDTO) {
        return repositoryService.getCourseChapterStudentProgressRepository()
                .save(courseChapterStudentProgressDTO.toEntity())
                .toDTO();
    }

    @Override
    public List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressFindAllByEachCourse(MemberDTO student, List<CourseDTO> courseList) {
        return courseChapterStudentProgressFindAllByEachCourse(student, courseList, courseChapterFindAllByCourseList(courseList));
    }

    @Override
    public List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressFindAllByEachCourse(MemberDTO student, List<CourseDTO> courseList, List<List<CourseChapterDTO>> chapterList) {
        List<List<CourseChapterStudentProgressDTO>> resultList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            List<CourseChapterStudentProgressDTO> courseChapterStudentProgressDTOList = new ArrayList<>();
            CourseStudentDTO courseStudentDTO = courseStudentFindByCourseAndStudent(courseList.get(i), student);
            for (int j = 0; j < chapterList.get(i).size(); j++) {
                CourseChapterDTO courseChapterDTO = chapterList.get(i).get(j);
                courseChapterStudentProgressDTOList.add(
                        courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(
                                courseChapterDTO, courseStudentDTO
                        )
                );
            }
            resultList.add(courseChapterStudentProgressDTOList);
        }
        return resultList;
    }

    @Override
    public double getMemeberProgress(MemberDTO student) {
        return getMemeberProgress(courseChapterStudentProgressFindAllByEachCourse(student, courseFindAllByMember(student)));
    }

    @Override
    public double getMemeberProgress(List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressList) {
        if (courseChapterStudentProgressList.isEmpty()) return 0.0;
        double progress = 0.0;
        for (List<CourseChapterStudentProgressDTO> courseChapterStudentProgressDTOS : courseChapterStudentProgressList) {
            if (courseChapterStudentProgressDTOS.isEmpty()) continue;
            // 각 코스 진도율 퍼센트
            double sum = 0.0;
            for (CourseChapterStudentProgressDTO courseChapterStudentProgressDTO : courseChapterStudentProgressDTOS) {
                if (courseChapterStudentProgressDTO.getCompleted()) sum++;
            }
            progress += sum / courseChapterStudentProgressDTOS.size();
        }
        progress = progress * 100 / courseChapterStudentProgressList.size();
        progress = Math.round(progress * 100) / 100.0;
        return Math.min(progress, 100.0);
    }

    @Override
    public double getCourseProgress(MemberDTO studentDTO, CourseDTO courseDTO) {
        List<CourseChapterDTO> courseChapterDTOList = courseChapterFindByCourse(courseDTO);

        if (courseChapterDTOList.isEmpty()) return 0.0;

        List<CourseChapterStudentProgressDTO> courseChapterStudentProgressDTOList = new ArrayList<>();
        for (CourseChapterDTO courseChapterDTO : courseChapterDTOList) {
            courseChapterStudentProgressDTOList.add(
                    courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(
                            courseChapterDTO, courseStudentFindByCourseAndStudent(courseDTO, studentDTO)
                    )
            );
        }

        double result = 0.0;
        for (CourseChapterStudentProgressDTO courseChapterStudentProgressDTO : courseChapterStudentProgressDTOList) {
            if (courseChapterStudentProgressDTO.getCompleted()) result++;
        }
        result = result * 100 / courseChapterStudentProgressDTOList.size();
        result = Math.round(result * 100) / 100.0;
        return Math.min(result, 100.0);
    }

    @Override
    public double getChapterProgress(MemberDTO studentDTO, CourseChapterDTO courseChapterDTO) {
        if (courseChapterDTO.getContentDTO() == null) return 0.0;
        if (courseChapterDTO.getContentDTO().getRunningTime() == 0) return 0.0;

        CourseChapterStudentProgressDTO courseChapterStudentProgressDTO = courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(
                courseChapterDTO, courseStudentFindByCourseAndStudent(courseChapterDTO.getCourseDTO(), studentDTO)
        );
        if (courseChapterStudentProgressDTO.getCompleted()) return 100.0;

        double result = (double) (courseChapterStudentProgressDTO.getMaxPosition() * 100) / courseChapterDTO.getContentDTO().getRunningTime();
        result = Math.round(result * 100) / 100.0;

        return Math.min(result, 100.0);
    }

    @Override
    public List<CourseChapterContent> courseChapterContentFindAll(){
        List<CourseChapterContent> all = repositoryService.getCourseChapterContentRepository().findAll();
        return all;
    }

    @Override
    public Page<Course> courseFindAllPage(Pageable pageable){
        return repositoryService.getCourseRepository().findAll(pageable);
    }

    @Override
    public Page<CourseChapter> courseChapterFindAllpage(Pageable pageable) {
        return repositoryService.getCourseChapterRepository().findAll(pageable);
    }

    @Override
    public Page<CourseStudent> courseStudentFindAllpage(Pageable pageable) {
        return repositoryService.getCourseStudentRepository().findAll(pageable);
    }

    @Override
    public Page<CourseStudent> courseStudentFindByCourse_courseSeq(Long courseSeq,Pageable pageable) {
        return repositoryService.getCourseStudentRepository().findByCourse_courseSeq(courseSeq,pageable);
    }
}
