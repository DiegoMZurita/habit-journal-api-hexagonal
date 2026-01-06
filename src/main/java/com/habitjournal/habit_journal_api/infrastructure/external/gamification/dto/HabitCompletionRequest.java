package com.habitjournal.habit_journal_api.infrastructure.external.gamification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitCompletionRequest {
    private Long userId;
    private String habitId;
}
