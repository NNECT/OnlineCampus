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
public class CommonCodeDTO implements DTOMarker {
    private String division;
    private String code;
    private String name;
    private Boolean inUse;
    private Integer orderNumber;
    private String note;
}
