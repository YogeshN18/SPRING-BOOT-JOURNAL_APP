package com.pratice.JournalApp_SpringBoot_Project.Entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Document(collection="Users")
public class User {

    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)// it must be  it will not create automatic need to add it in application properties
    private String userName;
    @NonNull
    private String password;

    private String email;

    private boolean sentimentAnalysis;


    @DBRef  // it means you are creating the reference of the journal entry in the user class whatever JE are storing in DB
    private List<JournalEntry> journalEntries =new ArrayList<>();

    private String role;




}
