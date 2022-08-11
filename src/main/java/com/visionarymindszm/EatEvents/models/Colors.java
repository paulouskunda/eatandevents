package com.visionarymindszm.EatEvents.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Document(collection = "colors")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Service
public class Colors {
    @Id
    private String id;

    private String color;
}
