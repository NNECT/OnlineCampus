package com.education.onlinecampus.repository.lecture;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Object> findByLoginId(String username);
}
