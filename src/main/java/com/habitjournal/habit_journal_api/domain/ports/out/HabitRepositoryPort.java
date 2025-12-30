package com.habitjournal.habit_journal_api.domain.ports.out;

import com.habitjournal.habit_journal_api.domain.Habit;

public interface HabitRepositoryPort {
    Habit save(Habit habit);
}
