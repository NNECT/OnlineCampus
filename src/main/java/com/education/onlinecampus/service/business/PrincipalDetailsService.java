    package com.education.onlinecampus.service.business;

    import com.education.onlinecampus.data.entity.Member;
    import com.education.onlinecampus.repository.MemberRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class PrincipalDetailsService implements UserDetailsService {

        private final MemberRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Member user = userRepository.findByUsername(username).orElseThrow();
            return new PrincipalDetails(user);
        }
    }
