package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.config.SecurityConfig;
import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.common.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final RepositoryService repositoryService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private SecurityConfig securityConfig;

    @GetMapping("/Member_login")
    public String MemberLogin(){
        return "lecture/MemberLogin";
    }
    @GetMapping("/Member_signup")
    public String GetMemberSignup(){
        return "/lecture/MemberJoin";
    }
    @PostMapping("/Member_signup")
    public String PostMemberSignup(@ModelAttribute MemberDTO member, @RequestParam("profileImage") MultipartFile profileImage){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.MemberSave(member);
        return "lecture/MemberLogin";
    }
    @GetMapping("/")
    public String Main(Model model){
        // 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Member loggedInMember = memberService.findByUserName(username);
            if (loggedInMember != null) {
                // 회원 정보를 모델에 추가하여 Thymeleaf 템플릿에서 사용 가능하게 함
                model.addAttribute("loggedInMember", loggedInMember);
            }
        }
        return "/lecture/MemberMain";
    }
}
