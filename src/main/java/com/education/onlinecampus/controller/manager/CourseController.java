package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.CommonCodeService;
import com.education.onlinecampus.service.common.YouTubeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CommonCodeService commonCodeService;
    private final MemberService memberService;
    private final CourseService courseService;
    private final YouTubeService youTubeService;
    private final ObjectMapper objectMapper;

    @GetMapping("/Course")
    public String Course(){
        return "manager/Course";}
    @PostMapping("/Course_save")
    @ResponseBody
    public ResponseEntity<String> CourseSave(@ModelAttribute CourseDTO courseDTO,Model model) {
        CommonCodeDTO byId = commonCodeService.findByCode(courseDTO.getStatusCodeDTO().getCode());
        courseDTO.setStatusCodeDTO(byId);
        Course course = courseService.CourseSave(courseDTO);
        try {
            String jsonResponse = objectMapper.writeValueAsString(course);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/Course_delete")
    @ResponseBody
    public Map<String, Object> deleteSelectedCourse(@RequestBody Map<String, List<String>> requestData) {
        Map<String, Object> response = new HashMap<>();
        List<String> selectedCourseSeqs = requestData.get("courseSeqs");
        try {
            boolean allEmpty = true;
            for (String s : selectedCourseSeqs) {
                List<CourseChapter> courseChapter = courseService.findCourseChapter(Long.valueOf(s));
                if (!courseChapter.isEmpty()) {
                    allEmpty = false;
                    break; // 하나라도 비어있지 않은 경우 루프를 종료
                }
            }
            if (allEmpty) {
                // 모든 강의에 대해 courseChapter가 비어있는 경우
                for (String s : selectedCourseSeqs) {
                    courseService.CourseDelete(courseService.CourseFind(Long.valueOf(s)).toEntity());
                }
                response.put("success", true);
            } else {
                response.put("success", false);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during deletion.");
        }

        return response;
    }
    @PostMapping("/Course_find")
  
    @ResponseBody
    public List<Course> CourseFind(@RequestParam("searchcoursename") String searchcoursename,
                                   @RequestParam("searchcoursename1") String searchcoursename1,
                                   @RequestParam("searchcourseStatus") String searchcourseStatus) {
        if (searchcoursename1.equals("")) {
            searchcoursename1 = "#";
        }
        List<Course> matchingCourses = new ArrayList<>();
        if(searchcoursename.equals("전체")){
            List<Course> courses = courseService.CourseFindAll();
            for (Course course: courses){
                if (searchcourseStatus.equals("전체")) {
                    matchingCourses.add(course);
                } else if (course.getStatusCode().getCode().equals(searchcourseStatus)) {
                    matchingCourses.add(course);
                }
            }
        }else {
            List<Course> byCourseNameContaining = courseService.findByCourseNameContaining(searchcoursename, searchcoursename1);
            for (Course course : byCourseNameContaining) {
                if (searchcourseStatus.equals("전체")) {
                    matchingCourses.add(course);
                } else if (course.getStatusCode().getCode().equals(searchcourseStatus)) {
                    matchingCourses.add(course);
                }
            }
        }
        return matchingCourses;
    }
  
    @GetMapping("/CourseChapter_save")
    public String GetCourseChapter_save(Model model){
        List<Course> courses = courseService.CourseFindAll();
        model.addAttribute("courses",courses);
        return "manager/CourseChapter";
    }
    @PostMapping("/CourseChapter_save")
    @ResponseBody
    public ResponseEntity<String> courseChapterSave(@ModelAttribute CourseChapterDTO courseChapter,Model model) {
        CourseChapter courseChapter1 = courseService.CourseChapterSave(courseChapter).toEntity();
        try {
            String jsonResponse = objectMapper.writeValueAsString(courseChapter1);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/CourseChapter_delete")
    @ResponseBody
    public Map<String, Object> deleteSelectedChapters(@RequestBody Map<String, List<String>> requestData) {
        Map<String, Object> response = new HashMap<>();
        List<String> selectedChapters = requestData.get("chapters");
        List<String> selectedCourseSeqs = requestData.get("courseSeqs");

        try {
            for (int i=0;i<selectedChapters.size();i++) {
                CourseChapter byCourseAndChapterOrder = courseService.findByCourseAndChapterOrder(Long.valueOf(selectedCourseSeqs.get(i)), Long.valueOf(selectedChapters.get(i)));
                courseService.CourseChapterDelete(byCourseAndChapterOrder);
            }
            List<CourseChapter> retrievedData = courseService.findByCourseChapterCompositeKeyCourseSeq(Long.valueOf(selectedCourseSeqs.get(0)));
            response.put("retrievedData", retrievedData);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during deletion.");
        }

        return response;
    }

    @PostMapping("/CourseStudent_delete")
    @ResponseBody
    public Map<String, Object> deleteSelectedStudents(@RequestBody Map<String, List<String>> requestData) {
        Map<String, Object> response = new HashMap<>();
        List<String> selectedstudents = requestData.get("students");
        List<String> selectedCourseSeqs1 = requestData.get("courseSeqs1");

        try {
            for (int i = 0; i < selectedstudents.size(); i++) {
                Long courseSeq = Long.valueOf(selectedCourseSeqs1.get(i));
                Long studentSeq = Long.valueOf(selectedstudents.get(i));

            }
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during deletion.");
        }

        return response;
    }

    @GetMapping("/Course_findAll")
    public String CourseFindAll(@ModelAttribute Course course, Model model){
        List<Course> courseList = courseService.CourseFindAll();
        model.addAttribute("courseList", courseList);
        return "lecture/courseList";
    }
    @GetMapping("/CourseChapterContent")
    public String CourseChapterContent(){
        return "manager/CourseChapterContent";
    }
    @PostMapping("/CourseChapterContent_save")
    public String CourseChapterContentSave(@ModelAttribute CourseChapterContentDTO courseChapterContentDTO, @RequestParam("viedo") MultipartFile multipartFile) throws IOException {
        youTubeService.uploadVideo(courseChapterContentDTO, multipartFile);
        return "redirect:/";
    }
    @GetMapping("/CourseStudent")
    public String CourseStudent(){
        return "manager/CourseStudent";
    }

    @PostMapping("/CourseStudent_save")
    @ResponseBody
    public ResponseEntity<List<CourseStudent>> CourseStudentSave(@RequestParam("CourseStudentcourseSeq") Long courseSeq,
                                                                 @RequestParam("cbox3") Long[] selectedMemberSeqs,
                                                                 CourseStudentDTO courseStudentDTO) {
        try {
            List<CourseStudent> courseStudents = courseService.CourseStudentAllSave(selectedMemberSeqs, courseSeq, courseStudentDTO);
            return ResponseEntity.ok(courseStudents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/CourseChapter_find")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> CourseChapterFind(@RequestParam("courseSeq") Long courseSeq) {
        try {
            Map<String, Object> response = new HashMap<>();

            List<CourseChapter> byCourseChapterCompositeKeyCourseSeq = courseService.findByCourseChapterCompositeKeyCourseSeq(courseSeq);
            List<CourseStudent> byCourseStudentCompositeKeyCourseSeq = courseService.courseStudentFindByCourseSeq(courseSeq);

            response.put("chapters", byCourseChapterCompositeKeyCourseSeq);
            response.put("students", byCourseStudentCompositeKeyCourseSeq);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/CourseChapter_detail")
    @ResponseBody
    public ResponseEntity<CourseChapter> CourseChapterDetail(@RequestParam("chapterOrder") Long chapterOrder, @RequestParam("courseSeq") Long courseSeq) {
        try {
            System.out.println(chapterOrder);
            System.out.println(courseSeq);
            CourseChapter byCourseCourseSeqAndChapterOrder = courseService.findByCourseAndChapterOrder(courseSeq, chapterOrder);
            return ResponseEntity.ok(byCourseCourseSeqAndChapterOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/CourseStudent_detail")
    @ResponseBody
    public ResponseEntity<CourseStudent> CourseStudentDetail(@RequestParam("studentSeq") Long studentSeq, @RequestParam("courseSeq") Long courseSeq) {
        try {
            System.out.println(studentSeq);
            System.out.println(courseSeq);
            CourseStudentDTO byCourseStudentCompositeKeyCourseSeqAndCourseStudentCompositeKeyStudentSeq =
                    courseService.courseStudentFindByCourseAndStudent(courseService.CourseFind(courseSeq), memberService.findBySeq(studentSeq));
            return ResponseEntity.ok(byCourseStudentCompositeKeyCourseSeqAndCourseStudentCompositeKeyStudentSeq.toEntity());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}