package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.config.ApplicationContextHolder;
import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CourseChapter", indexes = {
        @Index(name = "courseChapterOrderIdx", columnList = "courseSeq, chapterOrder")
})
@EntityListeners(CourseChapter.CourseChapterSeqListener.class)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapter implements EntityMarker<CourseChapterDTO> {
    /** 강좌 챕터 복합키 */
    @EmbeddedId
    private CourseChapterCompositeKey courseChapterCompositeKey;

    @Embeddable
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @EqualsAndHashCode
    public static class CourseChapterCompositeKey implements Serializable {
        /** 강좌 ID */
        private Long courseSeq;

        /** 챕터 ID
         * 입력하지 않아도 insert 시에 자동으로 채워짐 */
        @Column(nullable = false)
        private Long chapterSeq;

        private void setChapterSeq(Long chapterSeq) {
            this.chapterSeq = chapterSeq;
        }
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
    private Long chapterOrder;

    private void setChapterOrder(Long chapterOrder) {
        this.chapterOrder = chapterOrder;
    }

    /** 챕터 제목 */
    @Column(length = 150, nullable = false)
    private String chapterName;

    /** 콘텐츠 번호 */
    @ManyToOne
    @JoinColumn(
            name = "contentSeq",
            referencedColumnName = "videoId"
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

    @Override
    public CourseChapterDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }

    public static class CourseChapterSeqListener {
        @PrePersist
        public void prePersist(CourseChapter courseChapter) {
            EntityManager entityManager = ApplicationContextHolder.getBean(EntityManager.class);

            Long courseSeq = courseChapter.getCourseChapterCompositeKey().getCourseSeq();

            // Course 엔티티에 대한 트랜잭션 잠금 (다른 트랜잭션에서 해당 엔티티에 대한 수정을 못하도록 함)
            Course course = entityManager.find(Course.class, courseSeq, LockModeType.PESSIMISTIC_WRITE);

            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COALESCE(MAX(cc.courseChapterCompositeKey.chapterSeq), 0) FROM CourseChapter cc WHERE cc.courseChapterCompositeKey.courseSeq = :courseSeq",
                    Long.class
            );
            query.setParameter("courseSeq", courseSeq);
            Long maxChapterSeq = query.getSingleResult();

            courseChapter.getCourseChapterCompositeKey().setChapterSeq(maxChapterSeq + 1);
            courseChapter.setChapterOrder(maxChapterSeq + 1);
        }
    }
}
