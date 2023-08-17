package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeDTO implements DTOMarker<CommonCode> {
    private String code;
    private CommonCodeDivisionDTO divisionDTO;
    private String name;
    private Boolean inUse;
    private Integer orderNumber;
    private String note;

    @Override
    public CommonCode toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
