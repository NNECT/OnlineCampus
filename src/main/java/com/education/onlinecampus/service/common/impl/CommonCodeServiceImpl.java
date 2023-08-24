package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.CommonCodeDivision;
import com.education.onlinecampus.data.marker.DTOMarker;
import com.education.onlinecampus.service.common.CommonCodeService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {
    private final RepositoryService repositoryService;

    @Override
    public CommonCodeDTO findByCode(String code) {
        return repositoryService.getCommonCodeRepository().findById(code).orElseThrow().toDTO();
    }

    @Override
    public CommonCodeDTO findByDivisionAndCode(CommonCodeDivisionDTO division, int code) {
        return findByCode(String.format("%s%03d", division.getDivisionCode(), code));
    }

    @Override
    public CommonCodeDTO findByDivisionAndCode(String division, int code) {
        return findByCode(String.format("%s%03d", division, code));
    }

    @Override
    public CommonCodeDTO findByDivisionAndCode(char division, int code) {
        return findByCode(String.format("%s%03d", division, code));
    }

    @Override
    public CommonCodeDivisionDTO getDivision(String division) {
        return repositoryService.getCommonCodeDivisionRepository().findById(division).orElseThrow().toDTO();
    }

    @Override
    public <T extends DTOMarker> CommonCodeDivisionDTO getDivision(Class<T> clazz) {
        if (clazz.equals(MemberDTO.class)) return getDivision("M");
        if (clazz.equals(CourseDTO.class)) return getDivision("C");
        return null;
    }
}
