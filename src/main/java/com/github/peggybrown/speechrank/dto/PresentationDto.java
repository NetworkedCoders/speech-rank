package com.github.peggybrown.speechrank.dto;

import java.util.List;

import lombok.Data;

import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Presentation;
import com.github.peggybrown.speechrank.entity.Rate;

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
