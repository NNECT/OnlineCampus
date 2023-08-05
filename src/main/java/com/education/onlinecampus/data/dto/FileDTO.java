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
public class FileDTO implements DTOMarker {
    private Integer fileId;
    private String originalFileName;
    private String fileName;
    private Integer fileSize;
    private String fileGuid;
}
