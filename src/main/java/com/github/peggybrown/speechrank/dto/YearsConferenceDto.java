package com.github.peggybrown.speechrank.dto;

import lombok.Data;

import com.github.peggybrown.speechrank.entity.Conference;

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
