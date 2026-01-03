package com.habitjournal.habit_journal_api.application.exceptions;

public class HabitNotFoundException extends RuntimeException {
    public HabitNotFoundException(String id) {
        super("No se encontró el hábito con el ID: " + id);
    }
}
