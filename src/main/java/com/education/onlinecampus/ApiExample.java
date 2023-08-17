/**
 * Sample Java code for youtube.channels.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/code-samples#java
 */

package com.education.onlinecampus;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ApiExample implements ApplicationListener<ContextRefreshedEvent> {
    private final ResourceLoader resourceLoader;

    private static final String APPLICATION_NAME = "OnlineCampus";
    private static final String CLIENT_SECRETS = "classpath:joongang401projecta.json";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public GoogleCredentials authorize() throws IOException {
        InputStream in = resourceLoader.getResource(CLIENT_SECRETS).getInputStream();
        return GoogleCredentials.fromStream(in)
                .createScoped(Collections.singleton(YouTubeScopes.YOUTUBE_READONLY));
    }

    public YouTube getService() throws GeneralSecurityException, IOException {
        GoogleCredentials googleCredentials = authorize();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);
        return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            YouTube youtubeService = getService();
            // Define and execute the API request
            YouTube.Channels.List request = youtubeService.channels()
                    .list(Arrays.asList("snippet", "contentDetails", "statistics"));
            ChannelListResponse response = request.setMine(true).execute();
            System.out.println(response);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
