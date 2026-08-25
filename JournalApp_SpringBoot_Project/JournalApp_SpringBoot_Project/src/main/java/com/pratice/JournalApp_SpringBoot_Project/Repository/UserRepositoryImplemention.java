package com.pratice.JournalApp_SpringBoot_Project.Repository;

import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserRepositoryImplemention {


    @Autowired
    private MongoTemplate mongoTemplate;  // use to interact with MongoDB database directly



     public List<User> getUserForSA()
     {
         Query query = new Query();
         query.addCriteria(Criteria.where("userName").is("Lokesh"));
         query.addCriteria(Criteria.where("userName").is("Lokesh"));

         // we can add any and much more criteria here
         List<User> users = mongoTemplate.find(query, User.class);
         return  users;
     }

    public List<User> getUserForSA1()
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").isNull());

        // we can add any and much more criteria here
        List<User> users = mongoTemplate.find(query, User.class);
        return  users;
    }


    // we can use more operator in this

}
