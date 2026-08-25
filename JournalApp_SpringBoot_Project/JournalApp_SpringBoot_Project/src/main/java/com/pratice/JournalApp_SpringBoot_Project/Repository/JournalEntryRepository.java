package com.pratice.JournalApp_SpringBoot_Project.Repository;

import com.pratice.JournalApp_SpringBoot_Project.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
