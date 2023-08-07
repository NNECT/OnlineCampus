package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
    List<Member> findByGenderDivision(CommonCode code);
}
