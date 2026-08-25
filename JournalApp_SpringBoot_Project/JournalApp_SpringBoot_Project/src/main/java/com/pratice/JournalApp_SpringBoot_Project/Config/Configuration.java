package com.pratice.JournalApp_SpringBoot_Project.Config;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class Configuration
{

    public PlatformTransactionManager add(MongoDatabaseFactory mongoDatabaseFactory)
    {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}


