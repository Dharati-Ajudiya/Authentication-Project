package com.example.jwtdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2SuccessHandler  implements AuthenticationSuccessHandler {

    @Autowired
    JwtUserDetailsService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("AuthenticationSuccessHandler invoked");
        System.out.println("Authentication name: " + authentication.getName());
        System.out.println("request: " + request);
        System.out.println("response: " + response);
        System.out.println("Authentication: " + authentication);
        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

        userService.processOAuthPostLogin(oauthUser.getEmail(),oauthUser.getName());

    }
}
