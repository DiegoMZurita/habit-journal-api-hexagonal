package com.habitjournal.habit_journal_api.infrastructure.persistence.mongo;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.document.HabitDocument;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.repository.HabitMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class HabitMongoAdapter implements HabitRepositoryPort {
    private final HabitMongoRepository mongoRepository;
    private final HabitMongoMapper mapper;

    @Override
    public Habit save(Habit habit) {
        HabitDocument document = mapper.toDocument(habit);

        if(document.getId()==null){
            document.setId(Math.abs(ThreadLocalRandom.current().nextLong()));
        }

        HabitDocument savedDoc = mongoRepository.save(document);
        return mapper.toDomain(savedDoc);
    }

    @Override
    public Optional<Habit> findByName(String name) {
        return mongoRepository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public List<Habit> findAll() {
        return mongoRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Habit> findById(Long id) {
        return mongoRepository.findById(id).map(mapper::toDomain);
    }
}
