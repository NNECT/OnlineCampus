package com.education.onlinecampus.data.dto;

import com.education.onlinecampus.data.adapter.AdapterDTOToEntity;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.data.marker.DTOMarker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO implements DTOMarker<Member> {
    private Long memberSeq;
    private CommonCodeDTO memberDivisionDTO;
    private String nameKor;
    private LocalDate birthDate;
    private CommonCodeDTO genderCodeDTO;
    private String email;
    private String username;
    private String password;
    private LocalDate passwordChangeDate;
    private Boolean passwordChangeRequired;
    private Integer passwordErrorCount;
    private LocalDate lastLoginDate;
    private LocalDate registerDate;
    private FileDTO pictureFileDTO;
    private String description;

    @Override
    public Member toEntity() {
        return AdapterDTOToEntity.convert(this);
    }
}
