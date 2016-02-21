package com.github.peggybrown.speechrank.dtos;

import com.github.peggybrown.speechrank.entities.Conference;
import lombok.Data;

import java.util.List;

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
