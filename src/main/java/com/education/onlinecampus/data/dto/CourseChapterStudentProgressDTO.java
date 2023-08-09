package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseChapterStudentProgressDTO implements DTOMarker {
    private CourseDTO courseDTO;
    private CourseChapterDTO chapterDTO;
    private CourseStudentDTO studentDTO;
    private Integer finalPosition;
    private Integer maxPosition;
}
