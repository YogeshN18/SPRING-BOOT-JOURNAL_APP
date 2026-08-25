package com.pratice.JournalApp_SpringBoot_Project.Repository;

import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

        User findByUserName(String userName);

    void deleteByUserName(String userName);
}
