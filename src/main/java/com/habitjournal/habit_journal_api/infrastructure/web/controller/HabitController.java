package com.habitjournal.habit_journal_api.infrastructure.web.controller;

import com.habitjournal.habit_journal_api.domain.Habit;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitDtoMapper;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitRequestDTO;
import com.habitjournal.habit_journal_api.infrastructure.web.controller.dto.HabitResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {
    private final CreateHabitUseCase createHabitUseCase;
    private final HabitDtoMapper habitDtoMapper;

    @PostMapping
    public ResponseEntity<HabitResponseDTO> createNewHabit(@Valid @RequestBody HabitRequestDTO requestDTO) {
        Habit domainHabit = habitDtoMapper.toDomain(requestDTO);
        Habit createdHabit = createHabitUseCase.createHabit(domainHabit);

        return ResponseEntity.status(HttpStatus.CREATED).body(habitDtoMapper.toResponse(createdHabit));
    }
}

