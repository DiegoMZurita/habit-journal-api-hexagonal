package com.habitjournal.habit_journal_api.infrastructure.external.gamification;

import com.habitjournal.habit_journal_api.domain.ports.out.GamificationPort;
import com.habitjournal.habit_journal_api.infrastructure.external.gamification.dto.GamificationFeignClient;
import com.habitjournal.habit_journal_api.infrastructure.external.gamification.dto.HabitCompletionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GamificationAdapter implements GamificationPort {

    private final GamificationFeignClient feignClient;

    @Override
    public void notifyHabitCreation(Long userId, String habitId) {
        HabitCompletionRequest request = HabitCompletionRequest.builder()
                .userId(userId)
                .habitId(habitId)
                .build();

        try {
            feignClient.notifyHabitCompletion(request);
            System.out.println("Noficicación enviada a Gamification para el hábito: " + habitId);
        }catch(Exception e){
            System.out.println("Error al notificar a Gamification: " + e.getMessage() );
        }
    }
}
