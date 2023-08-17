package com.education.onlinecampus.data.adapter;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.data.mapper.*;

public class AdapterEntityToDTO {
    public static CommonCodeDivisionDTO convert(CommonCodeDivision entity) {
        return CommonCodeDivisionMapper.INSTANCE.toDTO(entity);
    }

    public static CommonCodeDTO convert(CommonCode entity) {
        return CommonCodeMapper.INSTANCE.toDTO(entity);
    }

    public static CourseDTO convert(Course entity) {
        return CourseMapper.INSTANCE.toDTO(entity);
    }

    public static CourseChapterDTO convert(CourseChapter entity) {
        return CourseChapterMapper.INSTANCE.toDTO(entity);
    }

    public static CourseChapterContentDTO convert(CourseChapterContent entity) {
        return CourseChapterContentMapper.INSTANCE.toDTO(entity);
    }

    public static CourseChapterStudentProgressDTO convert(CourseChapterStudentProgress entity) {
        return CourseChapterStudentProgressMapper.INSTANCE.toDTO(entity);
    }

    public static CourseStudentDTO convert(CourseStudent entity) {
        return CourseStudentMapper.INSTANCE.toDTO(entity);
    }

    public static FileDTO convert(File entity) {
        return FileMapper.INSTANCE.toDTO(entity);
    }

    public static MemberDTO convert(Member entity) {
        return MemberMapper.INSTANCE.toDTO(entity);
    }
}
