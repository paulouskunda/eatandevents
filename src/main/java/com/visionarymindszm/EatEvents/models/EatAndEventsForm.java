package com.visionarymindszm.EatEvents.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EatAndEventsForm {
    private String picnicPlannerName;
    private String picnicName;
    private String picnicTheme;
    private String picnicLocation;
    private String dateOfThePicnic;
    private String timeOfThePicnic;
    private String colorOfChoice;
}
