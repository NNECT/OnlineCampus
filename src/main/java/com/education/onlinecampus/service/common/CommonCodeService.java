package com.education.onlinecampus.service.common;

import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.dto.CommonCodeDivisionDTO;
import com.education.onlinecampus.data.marker.DTOMarker;

public interface CommonCodeService {
    CommonCodeDTO findByCode(String code);

    CommonCodeDTO findByDivisionAndCode(CommonCodeDivisionDTO division, int code);

    CommonCodeDTO findByDivisionAndCode(String division, int code);

    CommonCodeDTO findByDivisionAndCode(char division, int code);

    <T extends DTOMarker> CommonCodeDivisionDTO getDivision(String division);

    <T extends DTOMarker> CommonCodeDivisionDTO getDivision(Class<T> clazz);
}
