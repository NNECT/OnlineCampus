/**
 * Sample Java code for youtube.channels.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/code-samples#java
 */

package com.education.onlinecampus;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiExample implements ApplicationListener<ContextRefreshedEvent> {
    private final ResourceLoader resourceLoader;

    private static final String CLIENT_SECRETS= "classpath:client_secret.json";
    private static final Collection<String> SCOPES =
            List.of("https://www.googleapis.com/auth/youtube.readonly");

    private static final String APPLICATION_NAME = "API code samples";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Create an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException if the client_secret.json file cannot be found.
     */
    public Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        Resource resource = resourceLoader.getResource(CLIENT_SECRETS);
        GoogleClientSecrets clientSecrets;
        try (InputStream in = resource.getInputStream()) {
            // 여기에서 파일을 읽고 처리 =
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        }
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                        .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver.Builder().setPort(64044).build()).authorize("user");
    }

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException if YouTube's API key could not be
     * @throws IOException             if YouTube's API key could not be
     */
    public YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     */
//    public static void main(String[] args)
//            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
//        YouTube youtubeService = getService();
//        // Define and execute the API request
//        YouTube.Channels.List request = youtubeService.channels()
//                .list("snippet,contentDetails,statistics");
//        ChannelListResponse response = request.setId("UC_x5XG1OV2P6uZZ5FSM9Ttw").execute();
//        System.out.println(response);
//    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            YouTube youtubeService = getService();
            // Define and execute the API request
            YouTube.Channels.List request = youtubeService.channels()
                    .list("snippet,contentDetails,statistics");
            ChannelListResponse response = request.setMine(true).execute();
            System.out.println(response);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}