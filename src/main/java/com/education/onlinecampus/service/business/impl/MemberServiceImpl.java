package com.education.onlinecampus.service.business.impl;

import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.repository.MemberRepository;
import com.education.onlinecampus.service.common.RepositoryService;
import com.education.onlinecampus.service.common.impl.RepositoryServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {
    private final RepositoryService repositoryService;
    public List<Member> memberFromGender(CommonCode code){
        List<Member> byGenderDivision = repositoryService.getMemberRepository().findByGenderDivision(code);
        return byGenderDivision;

    }
}
