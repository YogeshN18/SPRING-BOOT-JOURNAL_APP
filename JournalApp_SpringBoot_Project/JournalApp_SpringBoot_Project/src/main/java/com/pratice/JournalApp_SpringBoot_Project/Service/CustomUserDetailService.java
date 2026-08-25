package com.pratice.JournalApp_SpringBoot_Project.Service;

import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byUserName = userRepository.findByUserName(username);
        if (byUserName != null) {

          return org.springframework.security.core.userdetails.User
                    .builder().username(byUserName.getUserName())
                    .password(byUserName.getPassword())
                    .roles(byUserName.getRole())
                    .build();

        }
        throw new UsernameNotFoundException(" user not found Exception"+username);
    }
}


