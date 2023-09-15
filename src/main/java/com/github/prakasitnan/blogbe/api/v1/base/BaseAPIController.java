package com.github.prakasitnan.blogbe.api.v1.base;

import com.github.prakasitnan.blogbe.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseAPIController {

    @Value("${url}")
    public String WEB_URL;

    public UserDetailsImpl getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }

}
