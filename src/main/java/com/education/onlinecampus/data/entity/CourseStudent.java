package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.CourseStudentDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseStudent")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseStudent implements EntityMarker<CourseStudentDTO> {
    /** 강좌 수강생 복합키 */
    @EmbeddedId
    private CourseStudentCompositeKey courseStudentCompositeKey;

    @Embeddable
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseStudentCompositeKey implements Serializable {
        /** 강좌 ID */
        private Long courseSeq;
        /** 수강생 ID */
        @Column(length = 10, nullable = false)
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

    /** 수강생 외래키 */
    @ManyToOne
    @JoinColumn(
            name = "studentSeq",
            referencedColumnName = "memberSeq",
            insertable = false, updatable = false, nullable = false
    )
    private Member student;

    @Override
    public CourseStudentDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
