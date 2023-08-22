package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.service.business.manager.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AjaxGetCourseChapterController {

    private final CourseService courseService;

    @RequestMapping("/ajax/getCourseChapter")
    public ResponseEntity<Map<String,Object>> getCourseChapter(@RequestParam("courseSeq") Long courseSeq){

        List<CourseChapter> courseChapterList = courseService.findCourseChapter(courseSeq);

        Map<String, Object> resultMap = new HashMap<>();

        if(courseChapterList!=null){
            resultMap.put("result","success");
            resultMap.put("courseChapterList",courseChapterList);
            resultMap.put("length",courseChapterList.size());
        }else {
            resultMap.put("result", "failure");
            resultMap.put("errorMessage", "챕터 없음!");
        }
        return ResponseEntity.ok(resultMap);

    }

}
