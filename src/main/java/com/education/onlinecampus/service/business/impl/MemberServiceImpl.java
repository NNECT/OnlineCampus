package com.education.onlinecampus.service.business.impl;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    public List<Member> genderfind(CommonCode code){
        List<Member> byGenderDivision =  memberRepository.findByGenderDivision(code);
        return byGenderDivision;

    }
}
