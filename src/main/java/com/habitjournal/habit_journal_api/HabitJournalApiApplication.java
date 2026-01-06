package com.habitjournal.habit_journal_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HabitJournalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitJournalApiApplication.class, args);
	}

}
