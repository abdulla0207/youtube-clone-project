package com.example.config;

import com.example.entity.ProfileEntity;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bad Cretetional");
        }

        ProfileEntity profile = optional.get();

        return new CustomUserDetails(profile);
    }
}
