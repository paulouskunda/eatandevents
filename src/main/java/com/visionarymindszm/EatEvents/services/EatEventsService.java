package com.visionarymindszm.EatEvents.services;

import com.visionarymindszm.EatEvents.models.*;
import com.visionarymindszm.EatEvents.repositories.EatEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EatEventsService {

    @Autowired
    private EatEventsRepository eatEventsRepository;

    @Autowired
    private ColorsRainbowService colorsRainbowService;

    public EatAndEvents createNewPicnicEvent(EatAndEventsForm eatAndEventsForm){
        EatAndEvents eatAndEvents = new EatAndEvents();
        eatAndEvents.setPicnicName(eatAndEventsForm.getPicnicName());
        eatAndEvents.setPicnicPlannerName(eatAndEventsForm.getPicnicPlannerName());
        eatAndEvents.setPicnicTheme(eatAndEventsForm.getPicnicTheme());
        eatAndEvents.setPicnicLocation(eatAndEventsForm.getPicnicLocation());
        String dateOfPicnic = eatAndEventsForm.getDateOfThePicnic() + " "+eatAndEventsForm.getTimeOfThePicnic();
        log.info(dateOfPicnic);
        try {
            Date dateOfThePicnic = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateOfPicnic);
            eatAndEvents.setDateOfThePicnic(dateOfThePicnic);
        }catch (Exception e){
            log.info("We encountered an error", e);
        }
        List<PicnicMembers> picnicMembersList = new ArrayList<>();
        picnicMembersList.add(new PicnicMembers(eatAndEventsForm.getPicnicPlannerName(), eatAndEventsForm.getColorOfChoice()));
        eatAndEvents.setPicnicMembers(picnicMembersList);
        return  eatEventsRepository.save(eatAndEvents);
    }

    public void addGroupMembersToPicnic(PicnicMemberForm picnicMemberForm){
        Optional<EatAndEvents> optionalEatAndEvents =
                eatEventsRepository.findById(picnicMemberForm.getPicnicIdValue());
        EatAndEvents eatAndEvents = optionalEatAndEvents.get();
        List<PicnicMembers> picnicMembersList = eatAndEvents.getPicnicMembers();

        picnicMembersList.add(new PicnicMembers(picnicMemberForm.getPicnicMemberForm(), picnicMemberForm.getPicnicColorForm()));
        eatAndEvents.setPicnicMembers(picnicMembersList);
        eatEventsRepository.save(eatAndEvents);
    }

    public EatAndEvents picnicDetailsAndMember(String picnicId){
        return eatEventsRepository.findById(picnicId).orElse(new EatAndEvents());
    }

    public List<String> openColors(){
        return colorsRainbowService.colorsOpen();
    }

    public List<String> picnicAvailableColors(List<String> picnicMembers) {
        List<String> availableColors = new ArrayList<>();
        openColors().forEach(colors -> {
            if (!picnicMembers.contains(colors)) {
                availableColors.add(colors);
            }
        });
        return availableColors;
    }
}
