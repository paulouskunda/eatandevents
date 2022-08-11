package com.visionarymindszm.EatEvents.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "eatEvents")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class EatAndEvents {

    @Id
    private String id;
    private String picnicPlannerName;
    private String picnicName;
    private String picnicTheme;
    private String picnicLocation;
    private Date dateOfThePicnic;
    private List<PicnicMembers> picnicMembers;

}
