package com.pratice.JournalApp_SpringBoot_Project.Controller;
import com.pratice.JournalApp_SpringBoot_Project.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal-entries")
public class JournalEntryController {

    private final Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();


    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }


    @PostMapping
    public JournalEntry CreateEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntry;
    }


    @GetMapping("id/{myId}")
    public JournalEntry getJournalId(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }



    @DeleteMapping("id/{myId}")
    public JournalEntry deleteByJournalId(@PathVariable Long myId) {
        return journalEntries.remove(myId);
    }



    @PutMapping("id/{myId}")
    public JournalEntry updateByJournalId(@PathVariable ObjectId myId, @RequestBody JournalEntry journalEntry) {
        journalEntries.put(myId, journalEntry);
        return journalEntry;
    }

    /*

    @GetMapping("/abc")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping("/Dfg")
    public void CreateEntry(){
    }

    */
}
