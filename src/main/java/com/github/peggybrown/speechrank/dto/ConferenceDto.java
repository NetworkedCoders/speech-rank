package com.github.peggybrown.speechrank.dto;

import java.util.List;

import lombok.Data;

import com.github.peggybrown.speechrank.entity.Conference;

@Data
public class ConferenceDto {
    String id;
    String name;
    List<PresentationDto> presentations;

    public ConferenceDto(Conference conference) {
        id = conference.getId();
        name = conference.getName();
        presentations = conference.getPresentations().map(PresentationDto::new).toJavaList();

    }
}
