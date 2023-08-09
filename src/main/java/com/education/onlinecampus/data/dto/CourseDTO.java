package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO implements DTOMarker {
    private Long courseSeq;
    private String courseName;
    private String courseBrief;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private CommonCodeDTO statusCode;
}
