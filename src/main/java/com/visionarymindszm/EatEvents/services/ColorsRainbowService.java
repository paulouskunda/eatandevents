package com.visionarymindszm.EatEvents.services;

import com.visionarymindszm.EatEvents.models.Colors;
import com.visionarymindszm.EatEvents.repositories.ColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorsRainbowService {

    @Autowired
    private ColorsRepository colorsRepository;

    public List<Colors> viewColors(){

        return colorsRepository.findAll();
    }

    public List<String> colorsOpen(){
        List<String> colors = new ArrayList<>();
        colorsRepository.findAll().forEach(colors1 -> colors.add(colors1.getColor()));

        return colors;
    }
}
