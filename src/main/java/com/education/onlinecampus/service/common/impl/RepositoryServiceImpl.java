package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.data.marker.DTOMarker;
import com.education.onlinecampus.data.marker.EntityMarker;
import com.education.onlinecampus.repository.*;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    private final CommonCodeDivisionRepository commonCodeDivisionRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final CourseChapterContentRepository courseChapterContentRepository;
    private final CourseChapterRepository courseChapterRepository;
    private final CourseChapterStudentProgressRepository courseChapterStudentProgressRepository;
    private final CourseRepository courseRepository;
    private final CourseStudentRepository courseStudentRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override
    public <E extends EntityMarker, T extends DTOMarker> E convertDTOToEntity(T dto) {
        if (dto instanceof CommonCodeDivisionDTO)
            return (E) AdapterDTOToEntity.convert((CommonCodeDivisionDTO) dto);
        if (dto instanceof CommonCodeDTO)
            return (E) AdapterDTOToEntity.convert((CommonCodeDTO) dto);
        if (dto instanceof CourseDTO)
            return (E) AdapterDTOToEntity.convert((CourseDTO) dto);
        if (dto instanceof CourseChapterDTO)
            return (E) AdapterDTOToEntity.convert((CourseChapterDTO) dto);
        if (dto instanceof CourseChapterContentDTO)
            return (E) AdapterDTOToEntity.convert((CourseChapterContentDTO) dto);
        if (dto instanceof CourseChapterStudentProgressDTO)
            return (E) AdapterDTOToEntity.convert((CourseChapterStudentProgressDTO) dto);
        if (dto instanceof CourseStudentDTO)
            return (E) AdapterDTOToEntity.convert((CourseStudentDTO) dto);
        if (dto instanceof FileDTO)
            return (E) AdapterDTOToEntity.convert((FileDTO) dto);
        if (dto instanceof MemberDTO)
            return (E) AdapterDTOToEntity.convert((MemberDTO) dto);
        return null;
    }

    @Override
    public <E extends EntityMarker, T extends DTOMarker> T convertEntityToDTO(E entity) {
        if (entity instanceof CommonCodeDivision)
            return (T) AdapterEntityToDTO.convert((CommonCodeDivision) entity);
        if (entity instanceof CommonCode)
            return (T) AdapterEntityToDTO.convert((CommonCode) entity);
        if (entity instanceof Course)
            return (T) AdapterEntityToDTO.convert((Course) entity);
        if (entity instanceof CourseChapter)
            return (T) AdapterEntityToDTO.convert((CourseChapter) entity);
        if (entity instanceof CourseChapterContent)
            return (T) AdapterEntityToDTO.convert((CourseChapterContent) entity);
        if (entity instanceof CourseChapterStudentProgress)
            return (T) AdapterEntityToDTO.convert((CourseChapterStudentProgress) entity);
        if (entity instanceof CourseStudent)
            return (T) AdapterEntityToDTO.convert((CourseStudent) entity);
        if (entity instanceof File)
            return (T) AdapterEntityToDTO.convert((File) entity);
        if (entity instanceof Member)
            return (T) AdapterEntityToDTO.convert((Member) entity);
        return null;
    }
}
