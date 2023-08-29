package com.education.onlinecampus.controller.lecture;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AsyncVideoController {
    private final MemberService memberService;
    private final CourseService courseService;

    @RequestMapping("/async/video_start_time")
    public ResponseEntity<Map<String, String>> videoStartTime(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        Long courseId = Long.parseLong(params.get("courseId"));
        Long chapterId = Long.parseLong(params.get("chapterId"));

        MemberDTO member = memberService.findByUserName(username);
        CourseDTO course = courseService.CourseFind(courseId);
        CourseChapterDTO chapter = courseService.courseChapterFindByCourseAndSeq(course, chapterId);
        CourseStudentDTO student = courseService.courseStudentFindByCourseAndMember(course, member);
        CourseChapterStudentProgressDTO progress = courseService.courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(chapter, student);

        return ResponseEntity.ok(Map.of(
                "videoStartTime", progress.getFinalPosition().toString(),
                "videoMaxTime", progress.getMaxPosition().toString(),
                "completed", progress.getCompleted().toString()
        ));
    }

    @RequestMapping("/async/video_time_check")
    public ResponseEntity<Map<String, String>> videoTimeCheck(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        Long courseId = Long.parseLong(params.get("courseId"));
        Long chapterId = Long.parseLong(params.get("chapterId"));
        Integer videoTime = Integer.parseInt(params.get("videoCurrentTime").split("\\.")[0]);

        try {
            MemberDTO member = memberService.findByUserName(username);
            CourseDTO course = courseService.CourseFind(courseId);
            CourseChapterDTO chapter = courseService.courseChapterFindByCourseAndSeq(course, chapterId);
            CourseStudentDTO student = courseService.courseStudentFindByCourseAndMember(course, member);
            CourseChapterStudentProgressDTO progress = courseService.courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(chapter, student);

            int maxTime = progress.getMaxPosition();

            // 기본 시간 차이는 1초
            // 오차 포함 3초 이상 차이가 나면 false
            if (maxTime + 3 < videoTime) {
                return ResponseEntity.ok(Map.of(
                        "result", "false",
                        "videoMaxTime", progress.getMaxPosition().toString()
                ));
            }

            progress.setFinalPosition(videoTime);

            // 최대 시간이 현재 시간보다 작으면 최대 시간을 현재 시간으로 변경
            if (progress.getMaxPosition() < videoTime) {
                progress.setMaxPosition(videoTime);
            }
            courseService.courseChapterStudentProgressSave(progress);

            return ResponseEntity.ok(Map.of(
                    "result", "true",
                    "videoMaxTime", progress.getMaxPosition().toString(),
                    "chapterProgress", String.valueOf(courseService.getChapterProgress(member, chapter))
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(Map.of(
                    "result", "false",
                    "videoMaxTime", "0"
            ));
        }
    }

    @RequestMapping("/async/video_end")
    public ResponseEntity<Map<String, String>> videoEnd(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        Long courseId = Long.parseLong(params.get("courseId"));
        Long chapterId = Long.parseLong(params.get("chapterId"));
        Integer videoTime = Integer.parseInt(params.get("videoCurrentTime").split("\\.")[0]);

        MemberDTO member = memberService.findByUserName(username);
        CourseDTO course = courseService.CourseFind(courseId);
        CourseChapterDTO chapter = courseService.courseChapterFindByCourseAndSeq(course, chapterId);
        CourseStudentDTO student = courseService.courseStudentFindByCourseAndMember(course, member);
        CourseChapterStudentProgressDTO progress = courseService.courseChapterStudentProgressFindByChapterAndStudentOrCreateNewInstance(chapter, student);

        int maxTime = progress.getMaxPosition();

        // 기본 시간 차이는 1초
        // 오차 포함 3초 이상 차이가 나면 false
        if (maxTime + 3 < videoTime) {
            return ResponseEntity.ok(Map.of(
                    "result", "false",
                    "videoMaxTime", progress.getMaxPosition().toString()
            ));
        }

        progress.setFinalPosition(videoTime);
        progress.setMaxPosition(videoTime);
        progress.setCompleted(true);
        courseService.courseChapterStudentProgressSave(progress);

        return ResponseEntity.ok(Map.of(
                "result", "true",
                "videoMaxTime", progress.getMaxPosition().toString(),
                "progressValue", String.valueOf(courseService.getMemeberProgress(member)),
                "courseProgress", String.valueOf(courseService.getCourseProgress(member, course)),
                "chapterProgress", String.valueOf(courseService.getChapterProgress(member, chapter))
        ));
    }
}
