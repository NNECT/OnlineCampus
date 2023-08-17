package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.adapter.AdapterEntityToDTO;
import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class Member implements EntityMarker<MemberDTO> {
    /** 회원 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long memberSeq;

    /** 회원 구분 */
    @ManyToOne
    @JoinColumn(
            name = "memberDivisionCode",
            referencedColumnName = "code",
            nullable = false
    )
    private CommonCode memberDivision;

    /** 이름 */
    @Column(length = 30, nullable = false)
    private String nameKor;

    /** 생년월일 */
    @Column
    private LocalDate birthDate;

    /** 성별 */
    @ManyToOne
    @JoinColumn(
            name = "genderCode",
            referencedColumnName = "code"
    )
    private CommonCode genderCode;

    /** 이메일 */
    @Column(length = 100, nullable = false)
    private String email;

    /** 로그인 아이디 */
    @Column(length = 40, nullable = false, unique = true)
    private String username;

    /** 비밀번호 */
    @Column(length = 150, nullable = false)
    private String password;

    /** 비밀번호 변경 날짜 */
    @Column(nullable = false) // default: now()
    private LocalDate passwordChangeDate;

    /** 비밀번호 변경 필요 여부 */
    @Column(nullable = false) // default: false
    private Boolean passwordChangeRequired;

    /** 비밀번호 오류 횟수 */
    @Column(nullable = false) // default: 0
    private Integer passwordErrorCount;

    /** 마지막 로그인 날짜 */
    @Column(nullable = false) // default: now()
    private LocalDate lastLoginDate;

    /** 가입 날짜 */
    @Column(nullable = false) // default: now()
    private LocalDate registerDate;

    /** 사진 파일 번호 */
    @ManyToOne
    @JoinColumn(
            name = "pictureFileSeq",
            referencedColumnName = "fileSeq"
    )
    private File pictureFile;

    /**
     * 새 데이터 저장 전 디폴트값 설정
     */
    @PrePersist
    public void prePersist() {
        if (this.passwordChangeDate == null) {
            this.passwordChangeDate = LocalDate.now();
        }
        if (this.passwordChangeRequired == null) {
            this.passwordChangeRequired = false;
        }
        if (this.passwordErrorCount == null) {
            this.passwordErrorCount = 0;
        }
        if (this.lastLoginDate == null) {
            this.lastLoginDate = LocalDate.now();
        }
        if (this.registerDate == null) {
            this.registerDate = LocalDate.now();
        }
    }

    @Override
    public MemberDTO toDTO() {
        return AdapterEntityToDTO.convert(this);
    }
}
