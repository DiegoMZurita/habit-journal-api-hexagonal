package com.habitjournal.habit_journal_api.infrastructure.persistence.jpa;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.entity.HabitEntity;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {
    public HabitEntity toEntity(Habit domain) {
        HabitEntity entity = new HabitEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }

    public Habit toDomain(HabitEntity entity) {
        Habit domain = new Habit();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        return domain;
    }
}
