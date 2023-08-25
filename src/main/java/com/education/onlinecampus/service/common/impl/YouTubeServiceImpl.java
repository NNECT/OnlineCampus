package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.data.dto.FileDTO;
import com.education.onlinecampus.service.common.RepositoryService;
import com.education.onlinecampus.service.common.YouTubeService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {
    private final YouTube youtube;
    private final RepositoryService repositoryService;
    private final ResourceLoader resourceLoader;

    @Override
    public ChannelListResponse getChannelList() throws IOException {
        YouTube.Channels.List request = youtube.channels()
                .list(Arrays.asList("snippet", "contentDetails", "statistics"));
        System.out.println("request: " + request);
        ChannelListResponse response = request.setMine(true).execute();
        System.out.println(response);
        return response;
    }

    @Override
    public List<Video> getAllUploadedVideos() throws IOException {
        // 사용자의 업로드 플레이리스트 ID를 가져옵니다.
        YouTube.Channels.List channelRequest = youtube.channels().list(List.of("contentDetails"));
        channelRequest.setMine(true);
        ChannelListResponse channelListResponse = channelRequest.execute();
        String uploadsPlaylistId = channelListResponse.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads();

        // 업로드 플레이리스트의 동영상 목록을 조회합니다.
        YouTube.PlaylistItems.List playlistItemsRequest = youtube.playlistItems().list(List.of("snippet"));
        playlistItemsRequest.setPlaylistId(uploadsPlaylistId);

        List<Video> videos = new ArrayList<>();
        String nextToken = "";
        do {
            playlistItemsRequest.setPageToken(nextToken);
            PlaylistItemListResponse playlistItemListResponse = playlistItemsRequest.execute();
            for (PlaylistItem playlistItem : playlistItemListResponse.getItems()) {
                Video video = new Video();

                // VideoSnippet 객체를 생성하고 PlaylistItemSnippet에서 필요한 정보를 가져와서 설정합니다.
                VideoSnippet videoSnippet = new VideoSnippet();
                PlaylistItemSnippet playlistItemSnippet = playlistItem.getSnippet();
                videoSnippet.setTitle(playlistItemSnippet.getTitle());
                videoSnippet.setDescription(playlistItemSnippet.getDescription());
                videoSnippet.setPublishedAt(playlistItemSnippet.getPublishedAt());
                videoSnippet.setThumbnails(playlistItemSnippet.getThumbnails());

                // PlaylistItem의 resourceId에서 동영상의 ID를 가져옵니다.
                String videoId = playlistItemSnippet.getResourceId().getVideoId();
                video.setId(videoId);

                // Video 객체에 VideoSnippet 객체를 설정합니다.
                video.setSnippet(videoSnippet);

                videos.add(video);
            }
            nextToken = playlistItemListResponse.getNextPageToken();
        } while (nextToken != null);

        return videos;
    }

    @Override
    public CourseChapterContentDTO uploadVideo(CourseChapterContentDTO content, MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("video", "tmp");

        try (InputStream inputStream = multipartFile.getInputStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        // 길이 구하기
        try (InputStream isForLength = Files.newInputStream(tempFile.toPath())) {
            int lengthInSeconds = getVideoLength(isForLength);
            content.setRunningTime(lengthInSeconds);
        }

        // 업로드
        try (InputStream isForUpload = Files.newInputStream(tempFile.toPath())) {
            content.setVideoId(uploadVideo(getVideoSnippet(content), getVideoStatus(), isForUpload));
        }

        // 임시 파일 삭제
        tempFile.delete();

        return repositoryService.getCourseChapterContentRepository().save(content.toEntity()).toDTO();
    }

    @Override
    public String uploadVideo(VideoSnippet snippet, VideoStatus status, InputStream videoStream) throws IOException {
        // Step 1: Video Object 생성
        Video video = new Video();
        video.setSnippet(snippet);
        video.setStatus(status);

        // Step 2: MediaContent 생성
        InputStreamContent mediaContent = new InputStreamContent("video/*", videoStream);

        // Step 3: Videos.insert 메서드 호출
        YouTube.Videos.Insert videoInsert = youtube.videos()
                .insert(Arrays.asList("snippet", "statistics", "status"), video, mediaContent);

        // Step 4: 업로드 실행
        Video uploadedVideo = videoInsert.execute();

        // 업로드된 동영상의 ID 출력
        return uploadedVideo.getId();
    }

    private VideoSnippet getVideoSnippet(CourseChapterContentDTO content) {
        VideoSnippet snippet = new VideoSnippet();
        snippet.setTitle(content.getContentName());
        snippet.setDescription(content.getContentBrief());
        snippet.setTags(Arrays.asList("test", "example"));
        return snippet;
    }

    private VideoStatus getVideoStatus() {
        VideoStatus status = new VideoStatus();
        status.setPrivacyStatus("unlisted");
        return status;
    }

    private int getVideoLength(InputStream inputStream) throws IOException {
        int lengthInSeconds = 0;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);
        grabber.start();
        lengthInSeconds = (int) (grabber.getLengthInTime() / 1000000);  // micro to seconds
        grabber.stop();
        return lengthInSeconds;
    }

    @Override
    public boolean setThumbnail(String videoId, FileDTO file) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/img/thumbnail/" + file.getFileName());
        try (InputStream inputStream = resource.getInputStream()) {
            InputStreamContent mediaContent = new InputStreamContent("image/jpeg", inputStream);
            YouTube.Thumbnails.Set thumbnailSet = youtube.thumbnails().set(videoId, mediaContent);
            ThumbnailSetResponse result = thumbnailSet.execute();
            return !result.getItems().isEmpty();
        }
    }
}