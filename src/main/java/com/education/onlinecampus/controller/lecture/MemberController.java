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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String PostMemberSignup(@ModelAttribute MemberDTO member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setMemberDivisionDTO(repositoryService.convertEntityToDTO(repositoryService.getCommonCodeRepository().findById("M002").orElseThrow()));
        member.setGenderCodeDTO(repositoryService.convertEntityToDTO(repositoryService.getCommonCodeRepository().findById("G001").orElseThrow()));
        memberService.MemberSave(member);
        return "lecture/MemberLogin";
    }
    @GetMapping("/")
    public String Main(){
        return "/lecture/MemberMain";
    }

}
