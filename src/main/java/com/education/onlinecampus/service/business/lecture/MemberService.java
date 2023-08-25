package com.education.onlinecampus.service.business.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;

import java.util.List;
import java.util.NoSuchElementException;

public interface MemberService {
    MemberDTO findByUserName(String username) throws NoSuchElementException;
    List<Member> MemberfindAll();
    void MemberSave(MemberDTO memberDTO);
}
