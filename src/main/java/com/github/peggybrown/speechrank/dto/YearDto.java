package com.github.peggybrown.speechrank.dto;

import java.util.List;

import lombok.Data;

import com.github.peggybrown.speechrank.entity.Year;

@Data
public class YearDto {
    String year;
    List<YearsConferenceDto> conferences;

    public YearDto(Year y) {
        year = y.getYear();
        conferences = y.getConferences().map(YearsConferenceDto::new).toJavaList();
    }
}
