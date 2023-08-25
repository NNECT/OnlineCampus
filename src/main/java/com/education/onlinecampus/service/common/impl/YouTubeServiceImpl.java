package com.education.onlinecampus.service.common.impl;

import com.education.onlinecampus.data.dto.CourseChapterContentDTO;
import com.education.onlinecampus.service.common.RepositoryService;
import com.education.onlinecampus.service.common.YouTubeService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {
    private final YouTube youtube;
    private final RepositoryService repositoryService;

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
        try (InputStream inputStream = multipartFile.getInputStream()) {
            content.setVideoId(uploadVideo(getVideoSnippet(content), getVideoStatus(), inputStream));
        }
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
}