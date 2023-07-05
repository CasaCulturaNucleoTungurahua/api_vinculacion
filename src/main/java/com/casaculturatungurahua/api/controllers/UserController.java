package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.MainUser;
import com.casaculturatungurahua.api.security.jwt.JWTProvider;
import com.casaculturatungurahua.api.security.jwt.JWTResponse;
import com.casaculturatungurahua.api.security.model.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public UserController(AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JWTResponse> login(@RequestBody MainUser user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JWTResponse jwtResponse = new JWTResponse(jwt, userPrincipal.getUsername());
        return ResponseEntity.ok(jwtResponse);
    }
}
