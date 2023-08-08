package com.education.onlinecampus.data.entity;

import com.education.onlinecampus.data.marker.EntityMarker;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Member implements EntityMarker {
    /** 회원 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            nullable = false
    )
    private Long memberSeq;

    /** 회원 구분 */
    @ManyToOne
    @JoinColumn(
            name = "memberDivisionCode",
            referencedColumnName = "code"
    )
    private CommonCode memberDivision;

    /** 이름 */
    @Column(
            length = 30,
            nullable = false
    )
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
    @Column(
            length = 100
    )
    private String email;

    /** 로그인 아이디 */
    @Column(length = 40, nullable = false, unique = true)
    private String loginId;

    /** 비밀번호 */
    @Column(length = 150, nullable = false)
    private String password;

    /** 비밀번호 변경 날짜 */
    @Column(nullable = false)
    @CreatedDate
    private LocalDate passwordChangeDate;

    /** 비밀번호 변경 필요 여부 */
    @Column
    private Boolean passwordChangeRequired;

    /** 비밀번호 오류 횟수 */
    @Column
    private Integer passwordErrorCount;

    /** 마지막 로그인 날짜 */
    @Column
    private LocalDate lastLoginDate;

    /** 가입 날짜 */
    @Column(nullable = false)
    @CreatedDate
    private LocalDate registerDate;

    /** 사진 파일 번호 */
    @ManyToOne
    @JoinColumn(
            name = "pictureFileSeq",
            referencedColumnName = "fileSeq"
    )
    private File pictureFile;
}
