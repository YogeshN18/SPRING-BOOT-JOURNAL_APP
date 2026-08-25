package com.pratice.JournalApp_SpringBoot_Project.Controller;


import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.Repository.UserRepository;
import com.pratice.JournalApp_SpringBoot_Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUser();

    }

//    @PostMapping
//    public void createUser(@RequestBody User user) {
//
//        userService.saveNewUser(user);
//        // this is without encreption
//        //userService.saveUser(user);
//
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserInDb = userService.findByUserName(userName);
        if (byUserInDb != null) {
            byUserInDb.setUserName(user.getUserName());
            byUserInDb.setPassword(user.getPassword());
            byUserInDb.setRole(user.getRole());
            userService.saveNewUser(byUserInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
