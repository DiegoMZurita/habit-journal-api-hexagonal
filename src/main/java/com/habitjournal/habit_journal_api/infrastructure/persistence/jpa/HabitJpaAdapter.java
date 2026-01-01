package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.repository.HabitJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HabitJpaAdapter implements HabitRepositoryPort {
    private final HabitJpaRepository jpaRepository;
    private final HabitJpaMapper mapper;

    @Override
    public Habit save(Habit habit) {
        HabitEntity habitEntity = mapper.toEntity(habit);

        HabitEntity savedEntity = jpaRepository.save(habitEntity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Habit> findByName(String name) {
        return jpaRepository.findByName(name).map(mapper::toDomain);
    }
}
