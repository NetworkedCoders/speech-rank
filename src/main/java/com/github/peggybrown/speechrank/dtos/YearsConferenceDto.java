package com.github.peggybrown.speechrank.dtos;


import com.github.peggybrown.speechrank.entities.Conference;
import lombok.Data;

@Data
public class YearsConferenceDto {
    private String id;
    private String name;
    private int presentations;

    public YearsConferenceDto(Conference c) {
        id = c.getId();
        name = c.getName();
        presentations = c.getPresentations().length();

    }
}
