package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseStudentDTO implements DTOMarker<CourseStudent> {
    private CourseDTO courseDTO;
    private MemberDTO studentDTO;

    @Override
    public CourseStudent toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
