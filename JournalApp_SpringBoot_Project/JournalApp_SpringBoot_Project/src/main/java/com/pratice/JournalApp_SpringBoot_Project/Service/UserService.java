package com.pratice.JournalApp_SpringBoot_Project.Service;

import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class UserService {


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }




    public void saveNewUser(User user) {

     try{
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);

     } catch (Exception e) {
      Logger.getLogger(UserService.class.getName()).severe("Error saving user xxxxxxxxxxxxxxxxxxxxx: " + e.getMessage());
      log.warn("Error saving user: {}", e.getMessage());
      log.info("User details: {}", user);   // actually log the user details for debugging  here
         log.trace("Stack trace: ", e);  // log the stack trace for debugging

     }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id.toString());
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

}

