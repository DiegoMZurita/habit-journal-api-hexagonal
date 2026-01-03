package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "habits")
public class HabitDocument {
    @Id
    private String id;
    private String name;

    List<LogEntryDocument> logEntries = new ArrayList<>();
}
