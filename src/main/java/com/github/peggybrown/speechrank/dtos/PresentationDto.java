package com.github.peggybrown.speechrank.dtos;


import com.github.peggybrown.speechrank.entities.Comment;
import com.github.peggybrown.speechrank.entities.Presentation;
import com.github.peggybrown.speechrank.entities.Rate;
import lombok.Data;

import java.util.List;

@Data
public class PresentationDto {
    private String id;
    private String title;
    private String link;
    private Double rating;

    private List<Rate> rates;
    private List<Comment> comments;

    public PresentationDto(Presentation p) {
        id = p.getId();
        title = p.getTitle();
        link = p.getLink();
        rating = p.getRating();
        rates = p.getRates().toJavaList();
        comments = p.getComments().toJavaList();
    }
}
