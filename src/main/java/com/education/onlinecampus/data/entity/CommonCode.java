package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CommonCode")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CommonCode implements EntityMarker {
    /** CommonCode 복합키 */
    @EmbeddedId
    private CommonCodeCompositeKey commonCodeCompositeKey;

    @Embeddable
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CommonCodeCompositeKey implements Serializable {
        /** 공통코드 구분 */
        @Column(
                length = 10,
                nullable = false
        )
        private String division;

        /** 공통코드 */
        @Column(
                length = 10,
                nullable = false
        )
        private String code;
    }

    /** 코드명 */
    @Column(
            length = 100,
            nullable = false
    )
    private String name;

    /** 사용 여부 */
    @Column(
            nullable = false
    )
    private Boolean inUse;

    /** 정렬 순서 */
    @Column(
            nullable = false
    )
    private Integer orderNumber;

    /** 비고 */
    @Column(
            length = 200
    )
    private String note;
}
