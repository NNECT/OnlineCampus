package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseChapterDTO implements DTOMarker<CourseChapter> {
    private CourseDTO courseDTO;
    private Long chapterSeq;
    private Integer chapterOrder;
    private String chapterName;
    private CourseChapterContentDTO contentDTO;
    private FileDTO supplementaryFileDTO;
    private String chapterBrief;

    @Override
    public CourseChapter toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
