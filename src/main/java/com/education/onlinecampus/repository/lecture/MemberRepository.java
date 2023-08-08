package com.education.onlinecampus.repository.lecture;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
}
