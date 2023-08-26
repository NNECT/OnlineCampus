package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.config.SecurityConfig;
import com.education.onlinecampus.data.dto.FileDTO;
import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapterContent;
import com.education.onlinecampus.data.entity.Member;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.ImageService;
import com.education.onlinecampus.service.common.CommonCodeService;
import com.education.onlinecampus.service.common.CommonCodeService;
import com.education.onlinecampus.service.common.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;
    private final ImageService imageService;
    private final CommonCodeService commonCodeService;

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
    @GetMapping("/Member_findAll")
    @ResponseBody
    public ResponseEntity<List<Member>> Member_findAll() {
        List<Member> members = memberService.MemberfindAll();
        return ResponseEntity.ok(members);
    }
    @PostMapping("/Member_signup")
    public String PostMemberSignup(@ModelAttribute MemberDTO member, @RequestParam("profileImage") MultipartFile profileImage) throws IOException {
        if(profileImage.isEmpty() || profileImage.equals(null)){

        }else {
            FileDTO fileDTO = imageService.saveProfileImage(profileImage);
            FileDTO fileSave = imageService.filesave(fileDTO);
            member.setPictureFileDTO(fileSave);
        }
        member.setMemberDivisionDTO(commonCodeService.findByDivisionAndCode('M', 3));
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
            MemberDTO loggedInMember = memberService.findByUserName(username);
            switch (loggedInMember.getMemberDivisionDTO().getCode()) {
                case "M001": {

                    //페이징 처리
                    int pageNumber = 0;
                    int pageSize = 5;
                    Pageable pageable = PageRequest.of(pageNumber, pageSize);
                    Page<Course> courses1 = courseService.courseFindAllPage(pageable);
                    model.addAttribute("courses",courses1);

                    List<CourseChapterContent> courseChapterContents = courseService.courseChapterContentFindAll();
                    model.addAttribute("courseChapterContents",courseChapterContents);

                    model.addAttribute("loggedInMember", loggedInMember);
                    List<Course> courses = courseService.CourseFindAll();
                    model.addAttribute("courses",courses);
                    return "/manager/manager_main";
                }
                case "M002":
                case "M003": {
                    return "redirect:lecture";
                }
                default: {
                    break;
                }
            }
        }
        return "redirect:Member_logout";
    }

    @GetMapping("/Member_update")
    public String updateForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            MemberDTO loggedInMember = memberService.findByUserName(username);
            model.addAttribute("loggedInMember", loggedInMember);
            return "lecture/MemberUpdate";
        }
        return "redirect:Member_logout";
    }

    @PostMapping("/loadPage")
    @ResponseBody
    public Page<Course> loadPage(@RequestParam("page") int pageNumber, Model model) {

        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Course> courses = courseService.courseFindAllPage(pageable);

        return courses;
    }
}