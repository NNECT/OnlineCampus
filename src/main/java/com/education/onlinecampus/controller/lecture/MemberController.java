package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                  @RequestParam("password") String password){
        System.out.println("로그인성공:"+password);
        return "lecture/MemberMain";
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
