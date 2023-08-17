package com.education.onlinecampus.data.adapter;

import com.education.onlinecampus.data.dto.*;
import com.education.onlinecampus.data.entity.*;
import com.education.onlinecampus.data.mapper.*;

public class AdapterDTOToEntity {
    public static CommonCodeDivision convert(CommonCodeDivisionDTO dto) {
        return CommonCodeDivisionMapper.INSTANCE.toEntity(dto);
    }

    public static CommonCode convert(CommonCodeDTO dto) {
        return CommonCodeMapper.INSTANCE.toEntity(dto);
    }

    public static Course convert(CourseDTO dto) {
        return CourseMapper.INSTANCE.toEntity(dto);
    }

    public static CourseChapter convert(CourseChapterDTO dto) {
        return CourseChapterMapper.INSTANCE.toEntity(dto);
    }

    public static CourseChapterContent convert(CourseChapterContentDTO dto) {
        return CourseChapterContentMapper.INSTANCE.toEntity(dto);
    }

    public static CourseChapterStudentProgress convert(CourseChapterStudentProgressDTO dto) {
        return CourseChapterStudentProgressMapper.INSTANCE.toEntity(dto);
    }

    public static CourseStudent convert(CourseStudentDTO dto) {
        return CourseStudentMapper.INSTANCE.toEntity(dto);
    }

    public static File convert(FileDTO dto) {
        return FileMapper.INSTANCE.toEntity(dto);
    }

    public static Member convert(MemberDTO dto) {
        return MemberMapper.INSTANCE.toEntity(dto);
    }
}
