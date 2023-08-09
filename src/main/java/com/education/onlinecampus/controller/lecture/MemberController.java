package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.service.business.lecture.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/Member_login")
    public String MemberLogin(){
        return "lecture/MemberLogin";
    }

    @PostMapping("/Member_login")
    public String PostMemberLogin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                          HttpServletRequest request){
        System.out.println("아이디:"+username);
        System.out.println("비밀번호:"+password);
        return "";
    }
    @GetMapping("/Member_signup")
    public String GetMemberSignup(){
        return "/lecture/MemberJoin";
    }
    @PostMapping("/Member_signup")
    public String PostMemberSignup(@ModelAttribute MemberDTO member){
        String encodepassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodepassword);
        memberService.MemberSave(member);
        return "/lecture/MemberLogin";
    }
}
