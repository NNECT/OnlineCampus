package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.dto.CommonCodeDTO;
import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.dto.CourseChapterDTO;
import com.education.onlinecampus.data.dto.CourseDTO;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.YouTubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final YouTubeService youTubeService;

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
    public String CourseFind(@ModelAttribute Course course, Model model){
        Course coursefind = courseService.CourseFind(course.getCourseSeq());
        model.addAttribute("coursefind",coursefind);
        return "manager/manager_main";
    }
    @GetMapping("/CourseChapter_save")
    public String GetCourseChapter_save(Model model){
        List<Course> courses = courseService.CourseFindAll();
        model.addAttribute("courses",courses);
        return "manager/CourseChapter";
    }
    @PostMapping("/CourseChapter_save")
    public String CourseChapterSave(@ModelAttribute CourseChapterDTO courseChapter){
        courseService.CourseChapterSave(courseChapter);
        return "redirect:/";
    }
    @PostMapping("/CourseChapter_delete")
    public String CourseChapterDelete(@ModelAttribute CourseChapter courseChapter){
        courseService.CourseChapterDelete(courseChapter);
        return "manager/manager_main";
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
        return "manager/manager_main";
    }
}
