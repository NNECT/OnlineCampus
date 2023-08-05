package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseChapterStudentProgress")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapterStudentProgress implements EntityMarker {
    /** 챕터 수강생 진도 복합키 */
    @EmbeddedId
    private CourseChapterStudentProgressCompositeKey courseChapterStudentProgressCompositeKey;

    @Embeddable
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseChapterStudentProgressCompositeKey implements Serializable {
        /** 강좌 ID */
        private Integer courseId;
        /** 챕터 ID */
        private Integer chapterId;
        /** 수강생 ID */
        private String studentId;
    }

    /** 강좌 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "courseId",
            referencedColumnName = "courseId",
            insertable = false, updatable = false
    )
    private Course course;

    /** 챕터 외래키 */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "courseId",
                    referencedColumnName = "courseId",
                    insertable = false, updatable = false
            ),
            @JoinColumn(
                    name = "chapterId",
                    referencedColumnName = "chapterId",
                    insertable = false, updatable = false
            )
    })
    private CourseChapter courseChapter;

    /** 수강생 외래키 */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "courseId",
                    referencedColumnName = "courseId",
                    insertable = false, updatable = false
            ),
            @JoinColumn(
                    name = "studentId",
                    referencedColumnName = "studentId",
                    insertable = false, updatable = false
            )
    })
    private CourseStudent courseStudent;

    /** 최종 재생 위치 */
    @Column
    private Integer finalPosition;

    /** 최대 재생 위치 */
    @Column
    private Integer maxPosition;
}
