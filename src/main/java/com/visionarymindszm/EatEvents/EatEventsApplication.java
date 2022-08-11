package com.visionarymindszm.EatEvents;

import com.visionarymindszm.EatEvents.models.EatAndEvents;
import com.visionarymindszm.EatEvents.models.PicnicMembers;
import com.visionarymindszm.EatEvents.services.ColorsRainbowService;
import com.visionarymindszm.EatEvents.services.EatEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class EatEventsApplication implements CommandLineRunner {

	@Autowired
	private ColorsRainbowService colorsRainbowService;

	@Autowired
	private EatEventsService eatEventsService;

	public static void main(String[] args) {
		SpringApplication.run(EatEventsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//		colorsRainbowService.viewColors();
//		eatEventsService.addGroupMembersToPicnic();


	}
}
