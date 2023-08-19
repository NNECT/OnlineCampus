package com.education.onlinecampus.data.entityListener;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PrePersist;
import javax.persistence.TypedQuery;

@Component
@Setter(onMethod_ = @Autowired)
public class CourseChapterSeqListener {
    private EntityManager entityManager;

    @PrePersist
    public void prePersist(CourseChapter courseChapter) {
        Long courseSeq = courseChapter.getCourseChapterCompositeKey().getCourseSeq();

        // Locking the specific course to prevent concurrent modifications
        Course course = entityManager.find(Course.class, courseSeq, LockModeType.PESSIMISTIC_WRITE);

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COALESCE(MAX(cc.courseChapterCompositeKey.chapterSeq), 0) FROM CourseChapter cc WHERE cc.courseChapterCompositeKey.courseSeq = :courseSeq",
                Long.class
        );
        query.setParameter("courseSeq", courseSeq);
        Long maxChapterSeq = query.getSingleResult();

        courseChapter.getCourseChapterCompositeKey().setChapterSeq(maxChapterSeq + 1);
    }
}
