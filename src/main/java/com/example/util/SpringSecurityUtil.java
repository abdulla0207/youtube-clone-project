package com.example.util;

import com.example.config.CustomUserDetails;
import com.example.entity.ProfileEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SpringSecurityUtil {

    public static ProfileEntity getCurrentEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) user.getAuthorities();
         Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return user.getProfile();
    }

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getProfile().getId();
    }
}
