package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.service.business.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServicel;
    @GetMapping("/Member_login")
    public String MemberLogin(){
        return "lecture/MemberLogin";
    }
}
