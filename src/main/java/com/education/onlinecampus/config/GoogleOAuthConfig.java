package com.education.onlinecampus.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.auth.oauth2.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Google OAuth2 인증을 위한 설정 클래스
 */
@Configuration
public class GoogleOAuthConfig {
    @Value("${google.oauth.client-secret.path}")
    private Resource clientSecretResource;

    @Value("${google.oauth.refresh-token.path}")
    private Resource refreshTokenResource;

    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Bean
    public HttpTransport httpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public GoogleClientSecrets googleClientSecrets() throws IOException {
        return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(clientSecretResource.getInputStream()));
    }

    @Bean
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow(GoogleClientSecrets clientSecrets, HttpTransport httpTransport) {
        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(YouTubeScopes.YOUTUBE)
        ).setAccessType("offline").build();
    }

    @Bean
    public OAuth2Credentials oAuth2Credentials(GoogleClientSecrets googleClientSecrets) throws IOException {
        String refreshToken = readRefreshToken(refreshTokenResource);
        return UserCredentials.newBuilder()
                .setClientId(googleClientSecrets.getDetails().getClientId())
                .setClientSecret(googleClientSecrets.getDetails().getClientSecret())
                .setRefreshToken(refreshToken)
                .build();
    }

    private String readRefreshToken(Resource resource) throws IOException {
        try (
                InputStream inputStream = resource.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            // 파일의 첫 번째 줄을 읽어 refresh_token으로 사용합니다.
            return reader.readLine();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Refresh token is missing. Re-authentication is required.");
        }
    }
}
