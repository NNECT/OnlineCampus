package com.education.onlinecampus.service.business.lecture.impl;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RepositoryService repositoryService;

    @Override
    public void MemberSave(MemberDTO memberDTO){
        repositoryService.getMemberRepository().save(repositoryService.convertDTOToEntity(memberDTO));
    }

    
}
