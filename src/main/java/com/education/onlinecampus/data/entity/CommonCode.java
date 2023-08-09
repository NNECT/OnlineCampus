package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CommonCode")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class CommonCode implements EntityMarker {
    /** 공통코드 */
    @Id
    @Column(length = 10, nullable = false)
    private String code;

    /** 공통코드 구분 */
    @Column(length = 10, nullable = false)
    private String division;

    /** 코드명 */
    @Column(length = 100, nullable = false)
    private String name;

    /** 사용 여부 */
    @Column(nullable = false)
    private Boolean inUse;

    /** 정렬 순서 */
    @Column(nullable = false)
    private Integer orderNumber;

    /** 비고 */
    @Column(length = 200)
    private String note;
}
