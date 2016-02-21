package com.github.peggybrown.speechrank.dtos;


import com.github.peggybrown.speechrank.entities.Year;
import lombok.Data;

import java.util.List;

@Data
public class YearDto {
    String year;
    List<YearsConferenceDto> conferences;

    public YearDto(Year y) {
        year = y.getYear();
        conferences = y.getConferences().map(YearsConferenceDto::new).toJavaList();
    }
}
