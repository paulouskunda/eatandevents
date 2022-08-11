package com.visionarymindszm.EatEvents.repositories;

import com.visionarymindszm.EatEvents.models.EatAndEvents;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EatEventsRepository extends MongoRepository<EatAndEvents, String> {
    Optional<EatAndEvents> findByPicnicPlannerName(String name);
}
