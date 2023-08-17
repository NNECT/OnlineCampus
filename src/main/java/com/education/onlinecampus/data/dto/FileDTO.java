package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.File;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO implements DTOMarker<File> {
    private Long fileSeq;
    private String originalFileName;
    private String fileName;
    private Integer fileSize;
    private String fileGuid;

    @Override
    public File toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
