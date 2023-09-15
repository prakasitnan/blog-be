package com.github.prakasitnan.blogbe.api.v1;

import com.github.prakasitnan.blogbe.api.v1.model.AuthReq;
import com.github.prakasitnan.blogbe.api.v1.model.AuthResp;
import com.github.prakasitnan.blogbe.model.UserEntity;
import com.github.prakasitnan.blogbe.repository.UserRepository;
import com.github.prakasitnan.blogbe.security.UserDetailsImpl;
import com.github.prakasitnan.blogbe.utils.JwtUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/signin", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> signin(@Valid AuthReq auth) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        userRepository.updateLastActivity(userDetails.getId());

        return ResponseEntity.ok(new AuthResp(userDetails.getId(), userDetails.getUsername(), jwt));
    }

//    public ResponseEntity<?> signup() {
//        UserEntity user = new UserEntity();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("1234"));
//        user.setEmail("test@mail.com");
//        user.setName("admin");
//        user.setUserRole(999L);
//        user.setStatusId(1L);
//        user.setCreateDate(new Timestamp(System.currentTimeMillis()));
//        user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
//        user = userRepository.save(user);
//        return ResponseEntity.ok(user);
//    }
}
