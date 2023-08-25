package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.dto.MemberDTO;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AsyncVideoController {
    private final MemberService memberService;
    private final CourseService courseService;

    @PostMapping("/async/video_start_time")
    public ResponseEntity<Map<String, String>> videoStartTime(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        Long courseId = Long.parseLong(params.get("courseId"));
        Long chapterId = Long.parseLong(params.get("chapterId"));

        MemberDTO member = memberService.findByUserName(username);
        CourseDTO course = courseService.CourseFind(courseId);
        CourseChapterDTO chapter = courseService.courseChapterFindByCourseAndSeq(course, chapterId);

        Map<String, String> result = new HashMap<>();
        return ResponseEntity.ok(result);
    }
}
