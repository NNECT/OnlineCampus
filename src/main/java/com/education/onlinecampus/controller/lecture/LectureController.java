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
import java.util.*;

@Controller
@RequiredArgsConstructor
public class LectureController {
    private final MemberService memberService;
    private final CourseService courseService;
    private final YouTubeService youTubeService;

    @GetMapping("/lecture")
    public String course(@AuthenticationPrincipal UserDetails userDetails, Model model){
        MemberDTO authMember = memberService.findByUserName(userDetails.getUsername());
        List<CourseDTO> courseList = courseService.courseFindAllByMember(authMember);
        List<List<CourseChapterDTO>> courseChapterList = courseService.courseChapterFindAllByCourseList(courseList);
        List<List<CourseChapterStudentProgressDTO>> courseChapterStudentProgressList = courseService.courseChapterStudentProgressFindAllByEachCourse(authMember, courseList, courseChapterList);
        Map<Long, Double> courseProgressMap = new HashMap<>();
        courseList.forEach(course -> courseProgressMap.put(course.getCourseSeq(), courseService.getCourseProgress(authMember, course)));
        Map<Long, Map<Long, Double>> chapterProgressMap = new HashMap<>();
        for (int i = 0; i < courseList.size(); i++) {
            Map<Long, Double> chapterProgress = new HashMap<>();
            chapterProgressMap.put(courseList.get(i).getCourseSeq(), chapterProgress);
            courseChapterList.get(i).forEach(chapter -> chapterProgress.put(chapter.getChapterSeq(), courseService.getChapterProgress(authMember, chapter)));
        }
        model.addAttribute("courses", courseList);
        model.addAttribute("courseChapters", courseChapterList);
        model.addAttribute("progressValue", courseService.getMemeberProgress(courseChapterStudentProgressList));
        model.addAttribute("courseProgressMap", courseProgressMap);
        model.addAttribute("chapterProgressMap", chapterProgressMap);
        return "lecture/MemberMain";
    }

    @GetMapping("/lecture/test")
    public String courseTest(@AuthenticationPrincipal UserDetails userDetails) throws IOException {
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
                        .runningTime(youTubeService.getVideoDurationInSeconds(video.getId()))
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
        } catch (NoSuchElementException e) {
            return "redirect:/Member_logout";
        }
        return "redirect:/lecture";
    }
}
