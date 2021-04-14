package com.example.jwtdemo.controller;

import com.example.jwtdemo.CustomException.ValidationException;
import com.example.jwtdemo.model.*;
import com.example.jwtdemo.service.CustomOAuth2UserService;
import com.example.jwtdemo.util.JwtTokenUtil;
import com.example.jwtdemo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.StandardSocketOptions;
import java.security.Principal;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   @Autowired
   private JwtUserDetailsService userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails);


        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token,userDetails.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody CustomUserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }


    /*@GetMapping(value = "/loginwithgoogle")
    public JwtResponse  user(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return oauthUserService.loginWithGoogle(oAuth2User);

    }*/

    @GetMapping(value = "/loginwithgoogle")
    public ModelAndView user(@AuthenticationPrincipal OAuth2User oAuth2User) {
        AuthResponse response = oauthUserService.loginWithGoogle(oAuth2User);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username" ,response.getUserName());
        modelAndView.addObject("name",response.getName());
        modelAndView.addObject("picture",response.getImg());
        modelAndView.addObject("token",response.getJwttoken());
        modelAndView.setViewName("user");
        return modelAndView;
    }


    private void authenticate(String username, String password) throws Exception {
        if(null == username || null == password || username.equals("") || password.equals("")){
            throw new ValidationException("username or password cannot be null");
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
