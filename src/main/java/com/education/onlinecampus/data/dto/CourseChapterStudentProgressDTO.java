package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.CourseChapterStudentProgress;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseChapterStudentProgressDTO implements DTOMarker<CourseChapterStudentProgress> {
    private CourseDTO courseDTO;
    private CourseChapterDTO chapterDTO;
    private CourseStudentDTO studentDTO;
    private Integer finalPosition;
    private Integer maxPosition;
    private Boolean completed;

    @Override
    public CourseChapterStudentProgress toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
