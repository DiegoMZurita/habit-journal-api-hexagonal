package com.habitjournal.habit_journal_api.infrastructure.web.controller.dto;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.LogEntry;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class HabitDtoMapper {
    public Habit toDomain(HabitRequestDTO dto) {
        Habit habit = new Habit();
        habit.setName(dto.getName());
        if(dto.getLogs() != null) {
            List<LogEntry> logs = dto.getLogs().stream()
                    .map(date ->{
                        LogEntry log = new LogEntry();
                        log.setEntryDate(date);
                        return log;
                    })
                    .toList();

            habit.setLogEntries(logs);
        }
        return habit;
    }

    public HabitResponseDTO toResponse(Habit habit) {
        List<LocalDateTime> logDates = habit.getLogEntries().stream()
                .map(LogEntry::getEntryDate).toList();
        return new HabitResponseDTO(habit.getId(), habit.getName(), logDates);
    }
}
