package com.pratice.JournalApp_SpringBoot_Project.Service;

import com.pratice.JournalApp_SpringBoot_Project.Entity.JournalEntry;
import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;


    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {

        User byUserName = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        byUserName.getJournalEntries().add(saved);
        userService.saveUser(byUserName);
    }

    public List<JournalEntry> getAllEntry() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id.toString());
    }


    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        User byUserName = userService.findByUserName(userName);
         removed = byUserName.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        if (removed) {

            userService.saveUser(byUserName);
            journalEntryRepository.deleteById(id.toString());
        }
        return removed;
    }
}

