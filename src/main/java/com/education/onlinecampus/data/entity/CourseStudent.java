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
        private Integer courseId;
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

    /** 수강생 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "studentId",
            referencedColumnName = "memberId",
            insertable = false, updatable = false
    )
    private Member student;
}
