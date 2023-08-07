package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseStudent")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseStudent implements EntityMarker {
    /** 강좌 수강생 복합키 */
    @EmbeddedId
    private CourseStudentCompositeKey courseStudentCompositeKey;

    @Embeddable
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseStudentCompositeKey implements Serializable {
        /** 강좌 ID */
        private Integer courseSeq;
        /** 수강생 ID */
        @Column(
                length = 10,
                nullable = false
        )
        private String studentSeq;
    }

    /** 강좌 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "courseSeq",
            referencedColumnName = "courseSeq",
            insertable = false, updatable = false
    )
    private Course course;

    /** 수강생 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "studentSeq",
            referencedColumnName = "memberSeq",
            insertable = false, updatable = false
    )
    private Member student;
}
