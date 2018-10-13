package com.library.service;

import com.library.model.StudentIdentify;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentIdentify studentIdentify = getUserByUsername(username);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new User(studentIdentify.getUsername(), encoder.encode(studentIdentify.getPassword()),
                AuthorityUtils.createAuthorityList(studentIdentify.getRole()));
    }

    public StudentIdentify getUserByUsername(String username) {
        return new StudentIdentify(1,"kritchat","055090323","ROLE_ADMIN");
    }
}
