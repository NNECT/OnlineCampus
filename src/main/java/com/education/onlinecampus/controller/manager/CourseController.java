package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.service.business.manager.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/Course")
    public String Course(){
        return "manager/Course";}

    @PostMapping("/Course_save")
    public String CourseSave(@ModelAttribute Course course){
        courseService.CourseSave(course);
        return "manager/manager_main";
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
    @GetMapping("/Course_findAll")
    public String CourseFindAll(Model model){
        System.out.println("오오오오오오오");
        List<Course> courseList = courseService.CourseFindAll();
        model.addAttribute("courseList", courseList);
        return "lecture/courseList";
    }
    @PostMapping("/CourseChapter_save")
    public String CourseChapterSave(@ModelAttribute CourseChapter courseChapter){
        courseService.CourseChapterSave(courseChapter);
        return "manager/manager_main";
    }
    @PostMapping("/CourseChapter_delete")
    public String CourseChapterDelete(@ModelAttribute CourseChapter courseChapter){
        courseService.CourseChapterDelete(courseChapter);
        return "manager/manager_main";
    }
}
