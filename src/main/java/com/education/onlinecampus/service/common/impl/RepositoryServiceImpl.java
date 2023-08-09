package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.data.mapper.*;
import com.education.onlinecampus.data.marker.*;
import com.education.onlinecampus.repository.*;
import com.education.onlinecampus.repository.MemberRepository;
import com.education.onlinecampus.repository.CourseChapterRepository;
import com.education.onlinecampus.repository.CourseRepository;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final CommonCodeRepository commonCodeRepository;
    private final CourseChapterContentRepository courseChapterContentRepository;
    private final CourseChapterRepository courseChapterRepository;
    private final CourseChapterStudentProgressRepository courseChapterStudentProgressRepository;
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override public CommonCodeRepository getCommonCodeRepository() {
        return commonCodeRepository;
    }
    @Override public CourseChapterContentRepository getCourseChapterContentRepository() {
        return courseChapterContentRepository;
    }
    @Override public CourseChapterRepository getCourseChapterRepository() {
        return courseChapterRepository;
    }
    @Override public CourseChapterStudentProgressRepository getCourseChapterStudentProgressRepository() {
        return courseChapterStudentProgressRepository;
    }
    @Override public CourseRepository getCourseRepository() {
        return courseRepository;
    }
    @Override public CourseStudentRepository getCourseStudentRepository() {
        return courseStudentRepository;
    }
    @Override public FileRepository getFileRepository() {
        return fileRepository;
    }
    @Override public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public <E extends EntityMarker, T extends DTOMarker> E convertDTOToEntity(T dto) {
        if (dto instanceof CommonCodeDTO)
            return (E) CommonCodeMapper.INSTANCE.toEntity((CommonCodeDTO) dto);
        if (dto instanceof CourseDTO)
            return (E) CourseMapper.INSTANCE.toEntity((CourseDTO) dto);
        if (dto instanceof CourseChapterDTO)
            return (E) CourseChapterMapper.INSTANCE.toEntity((CourseChapterDTO) dto);
        if (dto instanceof CourseChapterContentDTO)
            return (E) CourseChapterContentMapper.INSTANCE.toEntity((CourseChapterContentDTO) dto);
        if (dto instanceof CourseChapterStudentProgressDTO)
            return (E) CourseChapterStudentProgressMapper.INSTANCE.toEntity((CourseChapterStudentProgressDTO) dto);
        if (dto instanceof CourseStudentDTO)
            return (E) CourseStudentMapper.INSTANCE.toEntity((CourseStudentDTO) dto);
        if (dto instanceof FileDTO)
            return (E) FileMapper.INSTANCE.toEntity((FileDTO) dto);
        if (dto instanceof MemberDTO)
            return (E) MemberMapper.INSTANCE.toEntity((MemberDTO) dto);
        return null;
    }

    @Override
    public <E extends EntityMarker, T extends DTOMarker> T convertEntityToDTO(E entity) {
        if (entity instanceof CommonCode)
            return (T) CommonCodeMapper.INSTANCE.toDTO((CommonCode) entity);
        if (entity instanceof Course)
            return (T) CourseMapper.INSTANCE.toDTO((Course) entity);
        if (entity instanceof CourseChapter)
            return (T) CourseChapterMapper.INSTANCE.toDTO((CourseChapter) entity);
        if (entity instanceof CourseChapterContent)
            return (T) CourseChapterContentMapper.INSTANCE.toDTO((CourseChapterContent) entity);
        if (entity instanceof CourseChapterStudentProgress)
            return (T) CourseChapterStudentProgressMapper.INSTANCE.toDTO((CourseChapterStudentProgress) entity);
        if (entity instanceof CourseStudent)
            return (T) CourseStudentMapper.INSTANCE.toDTO((CourseStudent) entity);
        if (entity instanceof File)
            return (T) FileMapper.INSTANCE.toDTO((File) entity);
        if (entity instanceof Member)
            return (T) MemberMapper.INSTANCE.toDTO((Member) entity);
        return null;
    }
}
