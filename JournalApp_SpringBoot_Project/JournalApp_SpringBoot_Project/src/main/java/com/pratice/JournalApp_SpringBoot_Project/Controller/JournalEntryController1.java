package com.pratice.JournalApp_SpringBoot_Project.Controller;


import com.pratice.JournalApp_SpringBoot_Project.Entity.JournalEntry;
import com.pratice.JournalApp_SpringBoot_Project.Entity.User;
import com.pratice.JournalApp_SpringBoot_Project.Service.JournalEntryService;
import com.pratice.JournalApp_SpringBoot_Project.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal-entries")
public class JournalEntryController1 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntryUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName = userService.findByUserName(userName);
        if (byUserName == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<JournalEntry> all = byUserName.getJournalEntries();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> CreateEntry(@RequestBody JournalEntry journalEntry) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntry.setDate(java.time.LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry, userName);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalId(@PathVariable ObjectId myId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName = userService.findByUserName(userName);
        List<JournalEntry> collect = byUserName.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteByJournalId(@PathVariable ObjectId myId) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean b = journalEntryService.deleteById(myId, userName);
        if (b) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateByJournalId(@PathVariable ObjectId myId, @RequestBody JournalEntry journalEntry) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User byUserName = userService.findByUserName(userName);
        List<JournalEntry> collect = byUserName.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).collect(Collectors.toList());


        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry1 = journalEntryService.findById(myId);
            if (journalEntry1.isPresent()) {

                JournalEntry old = journalEntry1.get() ;
                old.setTitle(journalEntry.getTitle());
                old.setContent(journalEntry.getContent());
                journalEntryService.saveEntry(old, userName);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
