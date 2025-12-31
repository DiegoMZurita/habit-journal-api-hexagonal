package com.habitjournal.habit_journal_api.infrastructure.web.controller.dto;

import com.habitjournal.habit_journal_api.domain.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitDtoMapper {
    public Habit toDomain(HabitRequestDTO dto) {
        Habit habit = new Habit();
        habit.setName(dto.getName());
        return habit;
    }

    public HabitResponseDTO toResponse(Habit habit) {
        return new HabitResponseDTO(habit.getId(), habit.getName());
    }
}
