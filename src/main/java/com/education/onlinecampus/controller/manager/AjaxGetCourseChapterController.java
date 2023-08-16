package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.service.business.manager.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AjaxGetCourseChapterController {

    private final CourseService courseService;

    @PostMapping ("/ajax/getCourseChapter")
    public ResponseEntity<Map<String,Object>> getCourseChapter(@RequestBody Map<String,String> map){

        System.out.println("1111111111111111111111");

        Long courseSeq = (long) Integer.parseInt(map.get("courseSeq"));
        List<CourseChapter> courseChapterList = courseService.findCourseChapter(courseSeq);

        Map<String, Object> resultMap = new HashMap<>();

        System.out.println(courseSeq);

        if(courseChapterList!=null){
            System.out.println("33333333333333");
            resultMap.put("result","success");
            resultMap.put("courseChapterList",courseChapterList);
            resultMap.put("length",courseChapterList.size());
        }else {
            resultMap.put("result", "failure");
            resultMap.put("errorMessage", "챕터 없음!");
        }
        System.out.println("444444444444444444444");
        return ResponseEntity.ok(resultMap);

    }

}
