package com.github.prakasitnan.blogbe.security;

import com.github.prakasitnan.blogbe.model.UserEntity;
import com.github.prakasitnan.blogbe.repository.UserRepository;
import com.github.prakasitnan.blogbe.service.security.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (loginAttemptService.isBlocked()) {
//            throw new RuntimeException("blocked");
//        }

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+ username));
        return UserDetailsImpl.build(user);
    }
}
