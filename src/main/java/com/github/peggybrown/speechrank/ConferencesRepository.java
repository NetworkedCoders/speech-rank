package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.dto.ConferenceDto;
import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.dto.YearDto;
import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Conference;
import com.github.peggybrown.speechrank.entity.Rate;

import java.util.List;

public interface ConferencesRepository {
    void add(Rate rate);

    void add(Comment comment);

    List<Conference> getConferences();

    List<YearDto> getYears();

    ConferenceDto getConference(String id);

    void importAllConferences();

    String importConference(ConferenceImportDto conf);
}
