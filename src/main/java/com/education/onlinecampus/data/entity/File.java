package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.FileDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "File")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class File implements EntityMarker<FileDTO> {
    /** 파일 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long fileSeq;

    /** 원본 파일명 */
    @Column(length = 200, nullable = false)
    private String originalFileName;

    /** 파일명 */
    @Column(length = 200, nullable = false)
    private String fileName;

    /** 파일 크기 */
    @Column
    private Integer fileSize;

    /** 파일 GUID */
    @Column(length = 40)
    private String fileGuid;

    @Override
    public FileDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
