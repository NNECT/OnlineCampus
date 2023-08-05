package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CourseChapterContent")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CourseChapterContent implements EntityMarker {
    /** 콘텐츠 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            nullable = false
    )
    private Integer contentId;

    /** 콘텐츠 이름 */
    @Column(
            length = 100,
            nullable = false
    )
    private String contentName;

    /** 콘텐츠 설명 */
    @Column(
            length = 400
    )
    private String contentBrief;

    /** 썸네일 파일 */
    @ManyToOne
    @JoinColumn(
            name = "thumbnailFileId",
            referencedColumnName = "fileId"
    )
    private File thumbnailFile;

    /** 콘텐츠 파일 */
    @ManyToOne
    @JoinColumn(
            name = "contentFileId",
            referencedColumnName = "fileId",
            nullable = false
    )
    private File contentFile;

    /** 유튜브 비디오 ID */
    @Column(
            length = 400
    )
    private String videoId;

    /** 강좌 상태 구분 공통 코드 */
    @Column(
            columnDefinition = "varchar(10)"
    )
    private String statusDivision;

    /** 동영상 길이 (초) */
    @Column
    private Integer runningTime;
}
