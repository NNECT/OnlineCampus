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
    @Override
    public Member findByUserName(String username){
        return repositoryService.getMemberRepository().findByUsername(username);
    }
    @Override
    public void changePassword(Member loggedInMember, String encryptedPassword) {
        loggedInMember.setPassword(encryptedPassword);
        repositoryService.getMemberRepository().save(loggedInMember);
}

}
