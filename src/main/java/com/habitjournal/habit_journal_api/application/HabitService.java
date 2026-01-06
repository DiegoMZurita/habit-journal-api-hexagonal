package com.habitjournal.habit_journal_api.application;

import com.habitjournal.habit_journal_api.application.exceptions.DuplicateHabitException;
import com.habitjournal.habit_journal_api.application.exceptions.HabitNotFoundException;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.in.RetrieveHabitsUseCase;
import com.habitjournal.habit_journal_api.domain.ports.out.GamificationPort;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HabitService implements CreateHabitUseCase, RetrieveHabitsUseCase {
    private final HabitRepositoryPort habitRepositoryPort;
    private final GamificationPort gamificationPort;

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
        Habit savedHabit = habitRepositoryPort.save(habit);
        gamificationPort.notifyHabitCreation(1L, savedHabit.getId());
        return savedHabit;
    }

    @Override
    public List<Habit> getHabits() {
        return habitRepositoryPort.findAll();
    }

    @Override
    public Habit getHabit(String id) {
        return habitRepositoryPort.findById(id).orElseThrow(
                () -> new HabitNotFoundException(id)
        );
    }
}
