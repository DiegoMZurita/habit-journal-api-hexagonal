package com.habitjournal.habit_journal_api.application.exceptions;

public class DuplicateHabitException extends RuntimeException {
    public DuplicateHabitException(String name) {
        super("El hábito '" + name + "' ya existe. No se permiten duplicados");
    }
}
