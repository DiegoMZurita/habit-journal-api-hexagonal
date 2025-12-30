package com.habitjournal.habit_journal_api.application;

import com.habitjournal.habit_journal_api.application.exceptions.DuplicateHabitException;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HabitService implements CreateHabitUseCase {
    private final HabitRepositoryPort habitRepositoryPort;

    @Override
    public Habit createHabit(Habit habit) {
        habitRepositoryPort.findByName(habit.getName()).ifPresent(
            existing ->{
                throw new DuplicateHabitException(habit.getName());
            }
        );

        if(!habit.hasValidName()){
            throw new IllegalArgumentException("El nombre del hábito no cumple con las reglas de negocio.");
        }
        return habitRepositoryPort.save(habit);
    }
}
