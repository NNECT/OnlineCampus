package com.education.onlinecampus.service.business.impl;

import com.education.onlinecampus.service.business.MemberService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RepositoryService repositoryService;

    
}
