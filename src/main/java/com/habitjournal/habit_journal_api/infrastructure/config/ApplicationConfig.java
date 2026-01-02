package com.habitjournal.habit_journal_api.infrastructure.config;

import com.habitjournal.habit_journal_api.application.HabitService;
import com.habitjournal.habit_journal_api.domain.ports.in.CreateHabitUseCase;
import com.habitjournal.habit_journal_api.domain.ports.in.RetrieveHabitsUseCase;
import com.habitjournal.habit_journal_api.domain.ports.out.HabitRepositoryPort;
import com.habitjournal.habit_journal_api.infrastructure.persistence.jpa.HabitJpaAdapter;
import com.habitjournal.habit_journal_api.infrastructure.persistence.mongo.HabitMongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
//    @Bean
//    public HabitRepositoryPort habitRepositoryPort(HabitJpaAdapter habitJpaAdapter) {
//        return habitJpaAdapter;
//    }
    @Bean
    public HabitRepositoryPort habitRepositoryPort(HabitMongoAdapter adapter) {
        return adapter;
    }

    @Bean
    public CreateHabitUseCase createHabitUseCase(HabitRepositoryPort habitRepositoryPort) {
        return new HabitService(habitRepositoryPort);
    }

    @Bean
    public RetrieveHabitsUseCase retrieveHabitsUseCase(HabitRepositoryPort habitRepositoryPort) {
        return new HabitService(habitRepositoryPort);
    }
}
