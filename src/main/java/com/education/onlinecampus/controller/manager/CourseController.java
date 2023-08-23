package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.business.manager.CourseService;
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

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final YouTubeService youTubeService;
    private final ObjectMapper objectMapper;

    @GetMapping("/Course")
    public String Course(){
        return "manager/Course";}

    @PostMapping("/Course_save")
    public String CourseSave(@ModelAttribute CourseDTO course,Model model){
        courseService.CourseSave(course);
        List<Course> courses = courseService.CourseFindAll();
        model.addAttribute("courses",courses);
        return "redirect:/";
    }
    @PostMapping("/Course_delete")
    public String CourseDelete(@ModelAttribute Course course){
        courseService.CourseDelete(course);
        return "manager/manager_main";
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
        CourseChapter courseChapter1 = courseService.CourseChapterSave(courseChapter);
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
                CourseChapter byCourseAndChapterOrder = courseService.findByCourseAndChapterOrder(Long.valueOf(selectedCourseSeqs.get(i)), Integer.valueOf(selectedChapters.get(i)));
                courseService.CourseChapterDelete(byCourseAndChapterOrder);
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
    public ResponseEntity<String> CourseStudentSave(@RequestParam("courseSeq") Long courseSeq,
                                                    @RequestParam("cbox3") Long[] selectedMemberSeqs,
                                                    CourseStudentDTO courseStudentDTO) {
        try {
            courseService.CourseStudentAllSave(selectedMemberSeqs, courseSeq, courseStudentDTO);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}