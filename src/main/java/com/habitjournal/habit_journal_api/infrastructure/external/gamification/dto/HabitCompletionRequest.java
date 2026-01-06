package com.habitjournal.habit_journal_api.infrastructure.external.gamification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HabitCompletionRequest {
    private Long userId;
    private String habitId;
}
