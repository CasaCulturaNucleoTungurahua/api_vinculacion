package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.DTO.UserResponse;
import com.casaculturatungurahua.api.entities.MainUser;
import com.casaculturatungurahua.api.repository.UserRepository;
import com.casaculturatungurahua.api.security.model.UserPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MainUser user = userRepository.findByUsername(username).orElseThrow( ()-> new RuntimeException(username + " not found"));
        return UserPrincipal.builder()
                .id(user.getId())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public ResponseEntity<UserResponse> save(MainUser user){
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MainUser userFromDB = userRepository.save(user);
        return ResponseEntity.ok(new UserResponse(userFromDB.getId(), userFromDB.getUsername()));
    }




}
