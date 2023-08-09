package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseChapter")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapter implements EntityMarker {
    /** 강좌 챕터 복합키 */
    @EmbeddedId
    private CourseChapterCompositeKey courseSessionCompositeKey;

    @Embeddable
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseChapterCompositeKey implements Serializable {
        /** 강좌 ID */
        private Long courseSeq;

        /** 챕터 ID */
        @Column(nullable = false)
        private Long chapterSeq;
    }

    /** 강좌 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "courseSeq",
            referencedColumnName = "courseSeq",
            insertable = false, updatable = false, nullable = false
    )
    private Course course;

    /** 순서 번호 */
    @Column(nullable = false)
    private Integer chapterOrder;

    /** 챕터 제목 */
    @Column(length = 150, nullable = false)
    private String chapterName;

    /** 콘텐츠 번호 */
    @OneToOne
    @JoinColumn(
            name = "contentSeq",
            referencedColumnName = "contentSeq"
    )
    private CourseChapterContent content;

    /** 보조자료 파일 번호 */
    @ManyToOne
    @JoinColumn(
            name = "supplementaryFileSeq",
            referencedColumnName = "fileSeq"
    )
    private File supplementaryFile;

    /** 챕터 설명 */
    @Column(length = 1000)
    private String chapterBrief;
}
