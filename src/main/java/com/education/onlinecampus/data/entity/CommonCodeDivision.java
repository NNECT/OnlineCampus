package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.CommonCodeDivisionDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CommonCodeDivision")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CommonCodeDivision implements EntityMarker<CommonCodeDivisionDTO> {
    /** 공통코드 구분 */
    @Id
    @Column(length = 3, nullable = false)
    private String divisionCode;

    /** 공통코드 구분명 */
    @Column(length = 100, nullable = false)
    private String divisionName;

    /** 사용 여부 */
    @Column(nullable = false) // default: true
    private Boolean inUse;

    /** 정렬 순서 */
    @Column(nullable = false) // default: 0
    private Integer orderNumber;

    /** 비고 */
    @Column(length = 200)
    private String note;

    /**
     * 새 데이터 저장 전 디폴트값 설정
     */
    @PrePersist
    public void prePersist() {
        if (this.inUse == null) {
            this.inUse = true;
        }
        if (this.orderNumber == null) {
            this.orderNumber = 0;
        }
    }

    @Override
    public CommonCodeDivisionDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
