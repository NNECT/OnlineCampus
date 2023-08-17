package com.education.onlinecampus.service.business.lecture;

import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.repository.MemberRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if(member != null){
            return new MemberDetails(member);
        }
        return null;
    }

    @Data
    public class MemberDetails implements UserDetails{

        private final Member member;

        public MemberDetails(Member member){this.member = member; }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> collect = new ArrayList<>();
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return member.getMemberDivision().getCode();
                }
            });
            return collect;
        }

        @Override
        public String getPassword() {
            return member.getPassword();
        }

        @Override
        public String getUsername() {
            return member.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }
}
