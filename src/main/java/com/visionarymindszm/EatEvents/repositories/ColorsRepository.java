package com.visionarymindszm.EatEvents.repositories;

import com.visionarymindszm.EatEvents.models.Colors;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ColorsRepository extends MongoRepository<Colors, String> {
}
