package com.habitjournal.habit_journal_api.application;

import com.habitjournal.habit_journal_api.application.exceptions.DuplicateHabitException;
import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import com.habitjournal.habit_journal_api.domain.ports.out.GamificationPort;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.memory.FakeHabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HabitServiceTest {

    private HabitService habitService;
    private HabitRepositoryPort habitRepository;
    private GamificationPort port;

    @BeforeEach
    void setUp() {
        // 1. Inyectamos manualmente el FakeRepository (versión String/UUID)
        habitRepository = new FakeHabitRepository();
        habitService = new HabitService(habitRepository, port);
    }

    @Test
    @DisplayName("Debe crear y recuperar un hábito correctamente (Happy Path)")
    void shouldCreateAndRetrieveHabit() {
        // GIVEN
        Habit newHabit = new Habit();
        newHabit.setName("Leer Clean Code");
        newHabit.setLogEntries(new ArrayList<>());

        // WHEN
        Habit savedHabit = habitService.createHabit(newHabit);

        // THEN
        assertNotNull(savedHabit.getId(), "El ID no debería ser nulo tras guardar");
        // Opcional: Verificar que parece un UUID o no está vacío
        assertFalse(savedHabit.getId().isEmpty());
        assertEquals("Leer Clean Code", savedHabit.getName());

        // Verificamos recuperando con el ID (que ahora es String)
        Habit foundHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(savedHabit.getId(), foundHabit.getId());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre está duplicado")
    void shouldThrowExceptionForDuplicateName() {
        // GIVEN
        Habit habit1 = new Habit();
        habit1.setName("Correr");
        habitService.createHabit(habit1);

        Habit habit2 = new Habit();
        habit2.setName("Correr"); // Nombre duplicado

        // WHEN & THEN
        assertThrows(DuplicateHabitException.class, () -> {
            habitService.createHabit(habit2);
        });
    }

    @Test
    @DisplayName("Debe persistir los LogEntries correctamente")
    void shouldPersistLogEntries() {
        // GIVEN
        Habit habit = new Habit();
        habit.setName("Meditar");

        LogEntry log1 = new LogEntry();
        log1.setEntryDate(LocalDateTime.now());

        habit.setLogEntries(List.of(log1));

        // WHEN
        Habit savedHabit = habitService.createHabit(habit);

        // THEN
        Habit retrievedHabit = habitService.getHabit(savedHabit.getId());
        assertEquals(1, retrievedHabit.getLogEntries().size());
    }
}