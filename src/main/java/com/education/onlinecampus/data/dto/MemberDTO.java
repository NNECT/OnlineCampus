package com.education.onlinecampus.data.dto;

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
public class MemberDTO implements DTOMarker {
    private Long memberSeq;
    private CommonCodeDTO memberDivision;
    private String nameKor;
    private LocalDate birthDate;
    private CommonCodeDTO genderCode;
    private String email;
    private String loginId;
    private String password;
    private LocalDate passwordChangeDate;
    private Boolean passwordChangeRequired;
    private Integer loginFailCount;
    private LocalDate lastLoginDate;
    private LocalDate registerDate;
    private FileDTO pictureFile;
}
