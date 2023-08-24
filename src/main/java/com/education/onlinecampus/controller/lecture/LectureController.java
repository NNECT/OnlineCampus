package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.YouTubeService;
import com.google.api.services.youtube.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class LectureController {
    private final MemberService memberService;
    private final CourseService courseService;
    private final YouTubeService youTubeService;

    @GetMapping("/lecture")
    public String course(){
        return "lecture/courseList";
    }

    @GetMapping("/lecture/course")
    public String courseDetail(@AuthenticationPrincipal UserDetails userDetails, Model model) throws IOException {
        try {
            MemberDTO authMember = memberService.findByUserName(userDetails.getUsername());

            CourseDTO courseDTO = courseService.CourseFind(2L);
            CourseStudentDTO courseStudentDTO = CourseStudentDTO.builder()
                    .courseDTO(courseDTO)
                    .studentDTO(authMember)
                    .build();
            courseService.courseStudentSave(courseStudentDTO);

            List<Video> videoList = youTubeService.getAllUploadedVideos();
            for (Video video : videoList) {
                CourseChapterContentDTO courseChapterContentDTO = CourseChapterContentDTO.builder()
                        .videoId(video.getId())
                        .contentName(video.getSnippet().getTitle())
                        .contentBrief(video.getSnippet().getDescription())
                        .build();
                courseChapterContentDTO = courseService.courseChapterContentSave(courseChapterContentDTO);

                CourseChapterDTO courseChapterDTO = CourseChapterDTO.builder()
                        .courseDTO(courseDTO)
                        .contentDTO(courseChapterContentDTO)
                        .chapterName(courseChapterContentDTO.getContentName())
                        .chapterBrief(courseChapterContentDTO.getContentBrief())
                        .build();
                courseService.CourseChapterSave(courseChapterDTO);
            }

            List<CourseDTO> courseList = courseService.courseFindAllByMember(authMember);
            List<List<CourseChapterDTO>> courseChapterList = courseService.courseChapterFindAllByCourseList(courseList);
            model.addAttribute("courses", courseList);
            model.addAttribute("courseChapters", courseChapterList);
        } catch (NoSuchElementException e) {
            return "redirect:Member_logout";
        }
        return "lecture/CourseTemp";
    }
}
