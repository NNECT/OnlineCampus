package com.education.onlinecampus.service.business.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;

public interface MemberService {
    void MemberSave(MemberDTO memberDTO);
    Member findByUserName(String username);
}
