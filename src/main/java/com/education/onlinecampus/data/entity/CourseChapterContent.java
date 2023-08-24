package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CourseChapterContent")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapterContent implements EntityMarker<CourseChapterContentDTO> {
    /** 유튜브 비디오 ID */
    @Id
    @Column(length = 400, nullable = false)
    private String videoId;

    /** 콘텐츠 이름 */
    @Column(length = 100, nullable = false)
    private String contentName;

    /** 콘텐츠 설명 */
    @Column(length = 400)
    private String contentBrief;

    /** 썸네일 파일 */
    @ManyToOne
    @JoinColumn(
            name = "thumbnailFileSeq",
            referencedColumnName = "fileSeq"
    )
    private File thumbnailFile;

    /** 동영상 길이 (초) */
    @Column
    private Integer runningTime;

    @Override
    public CourseChapterContentDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
