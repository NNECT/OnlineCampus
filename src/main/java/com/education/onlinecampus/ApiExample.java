/**
 * Sample Java code for youtube.channels.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/code-samples#java
 */

package com.education.onlinecampus;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ApiExample implements ApplicationListener<ContextRefreshedEvent> {
    private final ResourceLoader resourceLoader;
    private final GoogleClientSecrets googleClientSecrets;
    private final GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;

    private static final String APPLICATION_NAME = "OnlineCampus";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

    private String readRefreshToken() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:token");
        System.out.println("resource: " + resource.getURI());
        try (
                InputStream inputStream = resource.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            // 파일의 첫 번째 줄을 읽어 refresh_token으로 사용합니다.
            String line = reader.readLine();
            System.out.println("line: " + line);
            return line;
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Refresh token is missing. Re-authentication is required.");
        }
    }

    public OAuth2Credentials authorize() throws IOException {
        String refreshToken = readRefreshToken();
        return UserCredentials.newBuilder()
                .setClientId(googleClientSecrets.getDetails().getClientId())
                .setClientSecret(googleClientSecrets.getDetails().getClientSecret())
                .setRefreshToken(refreshToken)
                .build();
    }

    public YouTube getService() throws IOException, GeneralSecurityException {
        OAuth2Credentials credentials = authorize();
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            YouTube youtubeService = getService();
            System.out.println("youtubeService: " + youtubeService);
            // Define and execute the API request
            YouTube.Channels.List request = youtubeService.channels()
                    .list(Arrays.asList("snippet", "contentDetails", "statistics"));
            System.out.println("request: " + request);

            ChannelListResponse response = request.setMine(true).execute();
            System.out.println(response);
        } catch (IllegalStateException e) {
            // Redirect the user to the OAuth2 authorization page
            String url = googleAuthorizationCodeFlow.newAuthorizationUrl()
                    .setRedirectUri(REDIRECT_URI)
                    .build();

            System.out.println("Please authorize the application by visiting the following URL: " + url);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}