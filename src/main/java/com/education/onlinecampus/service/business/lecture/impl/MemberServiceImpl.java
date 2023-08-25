package com.education.onlinecampus.service.business.lecture.impl;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RepositoryService repositoryService;

    @Override
    public void MemberSave(MemberDTO memberDTO){
        repositoryService.getMemberRepository().save(repositoryService.convertDTOToEntity(memberDTO));
    }

    @Override
    public MemberDTO findBySeq(Long seq) throws NoSuchElementException {
        return repositoryService.getMemberRepository().findById(seq).orElseThrow().toDTO();
    }

    @Override
    public MemberDTO findByUserName(String username) throws NoSuchElementException {
        return repositoryService.getMemberRepository().findByUsername(username).orElseThrow().toDTO();
    }

    @Override
    public List<Member> MemberfindAll(){
        return  repositoryService.getMemberRepository().findAll();
    }
}
