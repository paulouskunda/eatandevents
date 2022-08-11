package com.visionarymindszm.EatEvents.controller;

import com.visionarymindszm.EatEvents.models.EatAndEvents;
import com.visionarymindszm.EatEvents.models.EatAndEventsForm;
import com.visionarymindszm.EatEvents.models.PicnicMemberForm;
import com.visionarymindszm.EatEvents.services.EatEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class EatAndEventsController {

    @Autowired
    private EatEventsService eatEventsService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("eatAndEvent", new EatAndEventsForm());
        List<String> openColors = eatEventsService.openColors();
        model.addAttribute("openColors",  openColors);
        return "home";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute(value="eatAndEvent") EatAndEventsForm eatAndEvent, Model model) {
        log.info(eatAndEvent.toString());
        EatAndEvents eatAndEvents = eatEventsService.createNewPicnicEvent(eatAndEvent);
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String urlBase = baseUrl+"/joinMembers/"+eatAndEvents.getId();
        model.addAttribute("baseURL", urlBase);
        String message = "*"+eatAndEvents.getPicnicPlannerName()+"* created a picnic *"+eatAndEvent.getPicnicName()
                +"* themed *"+eatAndEvent.getPicnicTheme()+"* and is inviting you to pick a color. Follow the link below" +
                " "+urlBase;
        model.addAttribute("whatsAppShare", message);
        log.info(baseUrl);
        log.info(eatAndEvents.toString());

        return "copyLink";
    }

    @PostMapping("/addMember")
    public String addMember(@ModelAttribute(value="picnicMembersForm") PicnicMemberForm picnicMembersForm, Model model) {
        log.info(picnicMembersForm.toString());
        eatEventsService.addGroupMembersToPicnic(picnicMembersForm);
        return "redirect:/joinMembers/"+picnicMembersForm.getPicnicIdValue(); //replace with redirect
    }


    @GetMapping("/joinMembers/{picnicId}")
    public String userColors(@PathVariable(value = "picnicId")String picnicId, Model model) throws ParseException {
        EatAndEvents eatAndEvents = eatEventsService.picnicDetailsAndMember(picnicId);
        model.addAttribute("id", picnicId);
        model.addAttribute("picnicMembersForm", new PicnicMemberForm());
        model.addAttribute("picnicMembers", eatAndEvents.getPicnicMembers());
        model.addAttribute("picnicDetails", eatAndEvents);
        List<String> alreadyUsed = new ArrayList<>();
        eatAndEvents.getPicnicMembers().forEach(userColors -> {
            alreadyUsed.add(userColors.getMemberColor());
        });
        List<String> availableColors = eatEventsService.picnicAvailableColors(alreadyUsed);
        model.addAttribute("availableColors",  availableColors);
        model.addAttribute("availableColorSize", availableColors.size());
        model.addAttribute("endDate", eatAndEvents.getDateOfThePicnic());

        return "memberColors";
    }


}
