package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.CourseChapterStudentProgressDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseChapterStudentProgress")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapterStudentProgress implements EntityMarker<CourseChapterStudentProgressDTO> {
    /** 챕터 수강생 진도 복합키 */
    @EmbeddedId
    private CourseChapterStudentProgressCompositeKey courseChapterStudentProgressCompositeKey;

    @Embeddable
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseChapterStudentProgressCompositeKey implements Serializable {
        /** 강좌 ID */
        private Long courseSeq;
        /** 챕터 ID */
        private Long chapterSeq;
        /** 수강생 ID */
        private Long studentSeq;
    }

    /** 강좌 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "courseSeq",
            referencedColumnName = "courseSeq",
            insertable = false, updatable = false, nullable = false
    )
    private Course course;

    /** 챕터 외래키 */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "courseSeq",
                    referencedColumnName = "courseSeq",
                    insertable = false, updatable = false, nullable = false
            ),
            @JoinColumn(
                    name = "chapterSeq",
                    referencedColumnName = "chapterSeq",
                    insertable = false, updatable = false, nullable = false
            )
    })
    private CourseChapter chapter;

    /** 수강생 외래키 */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "courseSeq",
                    referencedColumnName = "courseSeq",
                    insertable = false, updatable = false, nullable = false
            ),
            @JoinColumn(
                    name = "studentSeq",
                    referencedColumnName = "studentSeq",
                    insertable = false, updatable = false, nullable = false
            )
    })
    private CourseStudent student;

    /** 최종 재생 위치 */
    @Column
    private Integer finalPosition;

    /** 최대 재생 위치 */
    @Column(nullable = false)
    private Integer maxPosition;

    /**
     * 새 데이터 저장 전 디폴트값 설정
     */
    @PrePersist
    public void prePersist() {
        this.maxPosition = 0;
    }

    @Override
    public CourseChapterStudentProgressDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
