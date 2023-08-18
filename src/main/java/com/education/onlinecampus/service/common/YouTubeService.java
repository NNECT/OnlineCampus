package com.education.onlinecampus.service.common;

import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.dto.FileDTO;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface YouTubeService {
    ChannelListResponse getChannelList() throws IOException;

    List<Video> getAllUploadedVideos() throws IOException;

    String uploadVideo(VideoSnippet snippet, VideoStatus status, InputStream file) throws IOException;

    CourseChapterContentDTO uploadVideo(CourseChapterContentDTO content, FileDTO file) throws IOException;
}
