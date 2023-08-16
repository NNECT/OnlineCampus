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
public class CommonCodeDivisionDTO implements DTOMarker {
    private String divisionCode;
    private String divisionName;
    private Boolean inUse;
    private Integer orderNumber;
    private String note;
}
