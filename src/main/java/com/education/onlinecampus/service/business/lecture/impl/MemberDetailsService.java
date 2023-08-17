package com.education.onlinecampus.service.business.lecture.impl;

import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.repository.MemberRepository;
/*import com.education.onlinecampus.repository.lecture.lectureMemberRepository;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository; // MemberRepository는 실제 데이터베이스와 연동하는 역할을 수행해야 함

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = (Member) memberRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getLoginId())
                .password(member.getPassword())
                .roles("USER") // 사용자의 권한 (역할) 설정
                .build();
    }
}