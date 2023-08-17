package com.education.onlinecampus.controller.manager;

import com.education.onlinecampus.config.Constant;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
@RequiredArgsConstructor
public class OAuthController {
    private final GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;

    @RequestMapping("/oauth2callback")
    public String handleOAuth2Callback(@RequestParam("code") String authorizationCode) throws IOException {
        // authorization_code를 사용하여 access_token과 refresh_token 획득
        GoogleTokenResponse tokenResponse = googleAuthorizationCodeFlow.newTokenRequest(authorizationCode)
                .setRedirectUri(Constant.OAUTH_REDIRECT_URI)
                .execute();

        // refresh_token 저장
        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("refreshToken = \n" + refreshToken);

        // Save the refreshToken to a file
        saveTokenToFile(refreshToken);

        return "redirect:Member_login";
    }

    private void saveTokenToFile(String token) throws IOException {
        // Define the path of the file where the token should be saved
        // Assuming that the file "token" is located directly under the resources directory
        Path path = Paths.get("src", "main", "resources", "token");

        // Write the token to the file, overwriting the file if it already exists
        Files.write(path, token.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
