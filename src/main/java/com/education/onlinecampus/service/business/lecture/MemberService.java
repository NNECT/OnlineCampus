package com.education.onlinecampus.service.business.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;

import java.util.List;

public interface MemberService {
    void MemberSave(MemberDTO memberDTO);
    Member findByUserName(String username);
    List<Member> MemberfindAll();
}
