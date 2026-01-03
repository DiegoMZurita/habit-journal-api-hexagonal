package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.repository;

import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document.HabitDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitMongoRepository extends MongoRepository<HabitDocument, String> {
    Optional<HabitDocument> findByName(String name);
}
