package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.File;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMemberDivision_code(String divisionCode);
    List<Member> findByGenderCode(CommonCode genderCode);
    List<Member> findByPictureFile(File pictureFile);
    Optional<Member> findByUsername(String username);
}
