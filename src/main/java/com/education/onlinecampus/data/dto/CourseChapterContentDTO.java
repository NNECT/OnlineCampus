package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseChapterContentDTO implements DTOMarker<CourseChapterContent> {
    private String videoId;
    private String contentName;
    private String contentBrief;
    private FileDTO thumbnailFileDTO;
    private Integer runningTime;

    @Override
    public CourseChapterContent toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
