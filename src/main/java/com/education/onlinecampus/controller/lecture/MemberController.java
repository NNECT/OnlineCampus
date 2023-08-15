package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.service.business.lecture.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/Member_login")
    public String MemberLogin(){
        return "MemberLogin";
    }

/*    @PostMapping("/Member_login")
    public String PostMemberLogin(@RequestParam("username") String id,
                                  @RequestParam("password") String pw){
        System.out.println(id);
        System.out.println(pw);
        return "lecture/MemberLogin";
    }*/
    @GetMapping("/Member_signup")
    public String GetMemberSignup(){
        System.out.println("구분선!!!!!!!!-");
        return "/lecture/MemberJoin";
    }
    @PostMapping("/Member_signup")
    public String PostMemberSignup(@ModelAttribute MemberDTO member){
        System.out.println("받아와지나"+member.getPassword());
        String encodepassword = passwordEncoder.encode(member.getPassword());
        System.out.println("받아와지나1"+member.getPassword());
        member.setPassword(encodepassword);
        memberService.MemberSave(member);
        return "MemberLogin";
    }

    @GetMapping("/Member_main")
    public String MemberMain() {
        System.out.println("메인페이지로가라");
        return "/lecture/MemberMain";
    }
    @PostMapping("/Member_login")
    public String PostMemberLogin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  HttpSession session) {
        session.setAttribute("loggedInUser", username);

        return "/lecture/MemberMain";
    }

}
