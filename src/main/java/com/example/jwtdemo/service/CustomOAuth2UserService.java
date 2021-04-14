package com.example.jwtdemo.service;

import com.example.jwtdemo.Entity.AuthenticationProvider;
import com.example.jwtdemo.model.AuthResponse;
import com.example.jwtdemo.model.CustomUserDto;
import com.example.jwtdemo.model.JwtResponse;
import com.example.jwtdemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        System.out.println("CustomOAuth2UserService invoked");
        return new CustomOAuth2User(user);
    }

    /*public JwtResponse loginWithGoogle(OAuth2User oAuth2User) {
        String username = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("given_name");
        String picture = oAuth2User.getAttribute("picture");

        System.out.println(username );
        System.out.println(name);
        System.out.println(picture);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null){
            String token = jwtTokenUtil.generateToken(userDetails);
            return new JwtResponse(token,userDetails.getUsername());
        }
        else{
            return saveAndLogin(oAuth2User,username, name);
        }

    }

    private JwtResponse saveAndLogin(OAuth2User oAuth2User, String username, String name) {
        CustomUserDto dto = new CustomUserDto();
        dto.setName(name);
        dto.setUsername(username);
        dto.setAuthenticationprovider(AuthenticationProvider.GOOGLE);
        userDetailsService.save(dto);
        String token = jwtTokenUtil.generateToken(oAuth2User);
        return new JwtResponse(token, username);
    }*/

    public AuthResponse loginWithGoogle(OAuth2User oAuth2User) {
        String username = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("given_name");
        String picture = oAuth2User.getAttribute("picture");

        System.out.println(username );
        System.out.println(name);
        System.out.println(picture);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null){
            String token = jwtTokenUtil.generateToken(userDetails);
            return new AuthResponse(token,userDetails.getUsername(),name,picture);
        }
        else{
            return saveAndLogin(oAuth2User,username,name,picture);
            //return saveAndLogin(oAuth2User,username, name);
        }

    }

    private AuthResponse saveAndLogin(OAuth2User oAuth2User, String username, String name,String picture) {
        CustomUserDto dto = new CustomUserDto();
        dto.setName(name);
        dto.setUsername(username);
        dto.setAuthenticationprovider(AuthenticationProvider.GOOGLE);
        userDetailsService.save(dto);
        String token = jwtTokenUtil.generateToken(oAuth2User);
        return new AuthResponse(token,username,name,picture);
    }
}
