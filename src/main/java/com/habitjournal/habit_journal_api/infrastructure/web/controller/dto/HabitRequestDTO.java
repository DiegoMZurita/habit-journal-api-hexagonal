package com.habitjournal.habit_journal_api.infrastructure.web.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabitRequestDTO {
    @NotBlank(message = "El nombre del hábito no puede estar vacío.")
    private String name;
}
