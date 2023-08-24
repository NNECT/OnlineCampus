package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.config.SecurityConfig;
import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
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

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;

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
            switch (loggedInMember.getMemberDivision().getCode()) {
                case "M001": {
                    model.addAttribute("loggedInMember", loggedInMember);
                    List<Course> courses = courseService.CourseFindAll();
                    model.addAttribute("courses",courses);
                    List<CourseChapter> courseChapters = courseService.CourseChapterFindAll();
                    model.addAttribute("courseChapters",courseChapters);
                    return "/manager/manager_main";
                }
                case "M002": {
                    model.addAttribute("loggedInMember", loggedInMember);
                    return "/lecture/MemberMain";
                }
                case "M003": {
                    model.addAttribute("loggedInMember", loggedInMember);
                    return "/lecture/MemberMain";
                }
                case "M004": {
                    model.addAttribute("loggedInMember", loggedInMember);
                    return "/lecture/MemberUpdate";
                }
                default: {
                    break;
                }
            }
        }
        return "/lecture/courseList";
    }
    @GetMapping("/Header")
    public String Header(){
        return "lecture/header";
    }

    @GetMapping("/Member_update")
    public String updateForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member loggedInMember = memberService.findByUserName(username);
        model.addAttribute("loggedInMember",loggedInMember);
        return "lecture/MemberUpdate"; }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String currentPassword, @RequestParam String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member loggedInMember = memberService.findByUserName(username);

        // 현재 비밀번호 확인
        if (passwordEncoder.matches(currentPassword, loggedInMember.getPassword())) {
            // 새로운 비밀번호로 변경
            String encryptedPassword = passwordEncoder.encode(newPassword);
            memberService.changePassword(loggedInMember, encryptedPassword);

            // 비밀번호 변경 완료 후 로그아웃
            SecurityContextHolder.clearContext();
            return "redirect:/Member_login?passwordChanged";
        } else {
            // 현재 비밀번호가 일치하지 않을 때 처리
            return "redirect:/Member_update?error";
        }
    }
}