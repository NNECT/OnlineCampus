package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.File;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByMemberDivision(CommonCode memberDivision);
    List<Member> findByGenderCode(CommonCode genderCode);
    List<Member> findByPictureFile(File pictureFile);
    Member findByUsername(String username);
}
