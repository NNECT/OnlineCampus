package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Course")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class Course implements EntityMarker {
    /** 강좌 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long courseSeq;

    /** 강좌명 */
    @Column(length = 100, nullable = false)
    private String courseName;

    /** 강좌 소개 */
    @Column(length = 400)
    private String courseBrief;

    /** 강좌 시작 일시 */
    @Column(nullable = false) // default: now()
    private LocalDateTime startDateTime;

    /** 강좌 종료 일시 */
    @Column
    private LocalDateTime endDateTime;

    /** 강좌 진행 상태 */
    @ManyToOne
    @JoinColumn(
            name = "statusCode",
            referencedColumnName = "code",
            nullable = false
    )
    private CommonCode statusCode;

    /**
     * 새 데이터 저장 전 디폴트값 설정
     */
    @PrePersist
    public void prePersist() {
        if (this.startDateTime == null) {
            this.startDateTime = LocalDateTime.now();
        }
    }
}
