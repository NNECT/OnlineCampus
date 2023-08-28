package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.CommonCode;
import com.education.onlinecampus.data.entity.Course;
import com.education.onlinecampus.data.entity.CourseChapter;
import com.education.onlinecampus.data.entity.CourseStudent;
import com.education.onlinecampus.service.business.lecture.MemberService;
import com.education.onlinecampus.service.business.manager.CourseService;
import com.education.onlinecampus.service.common.CommonCodeService;
import com.education.onlinecampus.service.common.ImageService;
import com.education.onlinecampus.service.common.YouTubeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final ImageService imageService;

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
    public Page<Course> CourseFind(@RequestParam("searchcoursename") String searchcoursename,
                                   @RequestParam("searchcoursename1") String searchcoursename1,
                                   @RequestParam("searchcourseStatus") String searchcourseStatus) {
        int pageNumber = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

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
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), matchingCourses.size());
        Page<Course> page = new PageImpl<>(matchingCourses.subList(start, end), pageable, matchingCourses.size());

        return page;
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
        List<CourseChapter> courseChapter2 = courseService.findCourseChapter(courseChapter1.getCourse().getCourseSeq());
        try {
            String jsonResponse = objectMapper.writeValueAsString(courseChapter2.get(courseChapter2.size()-1));
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
                courseService.deleteByCourse_courseSeqAndStudent_memberSeq(Long.valueOf(selectedCourseSeqs1.get(i)),Long.valueOf(selectedstudents.get(i)));
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
    public String CourseChapterContentSave(@ModelAttribute CourseChapterContentDTO courseChapterContentDTO, @RequestParam("viedo") MultipartFile multipartFile,
                                           @RequestParam("thumbnailFile") MultipartFile thumbnailFile) throws IOException {
        if(thumbnailFile.isEmpty() || thumbnailFile.equals(null)){
        }else {
            FileDTO fileDTO = imageService.saveContentImage(thumbnailFile);
            FileDTO fileSave = imageService.filesave(fileDTO);
            courseChapterContentDTO.setThumbnailFileDTO(fileSave);
        }
        if(multipartFile.isEmpty() || multipartFile.equals(null)){
            courseChapterContentDTO.setVideoId("테스트아이디");
            courseService.courseChapterContentSave(courseChapterContentDTO);
        }else {
            youTubeService.uploadVideo(courseChapterContentDTO, multipartFile);
        }
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
            System.out.println("이거머냐"+courseSeq);
            List<CourseStudent> courseStudents = courseService.CourseStudentAllSave(selectedMemberSeqs, courseSeq, courseStudentDTO);
            for(CourseStudent courseStudent : courseStudents){
                System.out.println(courseStudent.getStudent().getMemberSeq());
            }
            System.out.println("몇개냐"+courseStudents.size());
            return ResponseEntity.ok(courseStudents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/CourseChapter_find")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> CourseChapterFind(@RequestParam("courseSeq") Long courseSeq) {
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        try {
            Pageable pageable = PageRequest.of(0, 10);

            Page<CourseChapter> byCourseChapterCompositeKeyCourseSeqPage = courseService.findByCourseChapterCompositeKeyCourseSeq(courseSeq, pageable);
            Page<CourseStudent> byCourseStudentCompositeKeyCourseSeq = courseService.courseStudentFindByCourseSeq(courseSeq, pageable);

            Map<String, Object> response = new HashMap<>();

            // 강의 데이터와 페이지 정보를 따로 담아서 전달
            Map<String, Object> chapterResponse = new HashMap<>();
            chapterResponse.put("content", byCourseChapterCompositeKeyCourseSeqPage.getContent());
            chapterResponse.put("totalPages", byCourseChapterCompositeKeyCourseSeqPage.getTotalPages());
            chapterResponse.put("number", byCourseChapterCompositeKeyCourseSeqPage.getNumber());
            chapterResponse.put("totalElements", byCourseChapterCompositeKeyCourseSeqPage.getTotalElements());
            response.put("chapters", chapterResponse);

            // 학생 데이터와 페이지 정보를 따로 담아서 전달
            Map<String, Object> studentResponse = new HashMap<>();
            studentResponse.put("content", byCourseStudentCompositeKeyCourseSeq.getContent());
            studentResponse.put("totalPages", byCourseStudentCompositeKeyCourseSeq.getTotalPages());
            studentResponse.put("number", byCourseStudentCompositeKeyCourseSeq.getNumber());
            studentResponse.put("totalElements",byCourseStudentCompositeKeyCourseSeq.getTotalElements());
            response.put("students", studentResponse);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/CourseChapter_detail")
    @ResponseBody
    public ResponseEntity<CourseChapter> CourseChapterDetail(@RequestParam("chapterOrder") Long chapterOrder, @RequestParam("courseSeq") Long courseSeq) {
        try {
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
            CourseStudentDTO byCourseStudentCompositeKeyCourseSeqAndCourseStudentCompositeKeyStudentSeq =
                    courseService.courseStudentFindByCourseAndStudent(courseService.CourseFind(courseSeq), memberService.findBySeq(studentSeq));
            return ResponseEntity.ok(byCourseStudentCompositeKeyCourseSeqAndCourseStudentCompositeKeyStudentSeq.toEntity());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}