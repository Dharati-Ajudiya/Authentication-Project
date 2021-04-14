package com.example.jwtdemo.service;

import com.example.jwtdemo.Entity.AuthenticationProvider;
import com.example.jwtdemo.Entity.CustomUser;
import com.example.jwtdemo.dao.CustomUserDao;
import com.example.jwtdemo.model.CustomUserDetail;
import com.example.jwtdemo.model.CustomUserDto;
import com.example.jwtdemo.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    CustomUserDao customUserDao;

    @Autowired
    PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser userCustom = customUserDao.findByUsername(username);

        if (userCustom == null) {
            return null;
           // throw new UsernameNotFoundException("User not found with username: " + username);

        } else {
            UserResponse response = entityToModel(userCustom);
            return new CustomUserDetail(userCustom);
        }
    }

    public UserResponse save(CustomUserDto user) {
        CustomUser userCustom = new CustomUser();
        userCustom.setName(user.getName());
        userCustom.setUsername(user.getUsername());
        userCustom.setAuthenticationProvider(user.getAuthenticationprovider());
       // userCustom.setAuthenticationProvider(AuthenticationProvider.LOCAL);
        if (user.getPassword() != null){
            userCustom.setPassword(bcryptEncoder.encode(user.getPassword()));
        }else{
            userCustom.setPassword(null);
        }

        CustomUser addUser = customUserDao.save(userCustom);
        return entityToModel(addUser);

    }

    private UserResponse entityToModel(CustomUser addUser) {
        UserResponse user = new UserResponse();
        user.setName(addUser.getName());
        user.setUsername(addUser.getUsername());
        return user;
    }

    public void processOAuthPostLogin(String email, String name) {
        CustomUser userCustom = new CustomUser();
        userCustom.setName(name);
        userCustom.setUsername(email);
        customUserDao.save(userCustom);
    }
}
