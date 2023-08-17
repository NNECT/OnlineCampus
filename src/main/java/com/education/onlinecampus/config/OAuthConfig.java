package com.education.onlinecampus.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTubeScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@Configuration
public class OAuthConfig {
    @Value("classpath:client_secret.json")
    private Resource clientSecretResource;

    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @Bean
    public GoogleClientSecrets googleClientSecrets() throws IOException {
        return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(clientSecretResource.getInputStream()));
    }

    @Bean
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow(GoogleClientSecrets clientSecrets) {
        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singleton(YouTubeScopes.YOUTUBE_READONLY)
        ).build();
    }
}
