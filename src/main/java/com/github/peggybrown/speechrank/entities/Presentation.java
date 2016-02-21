package com.github.peggybrown.speechrank.entities;


import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Presentation {

    private String id;
    private String title;
    private String link;
    private Double rating;
    private List<Rate> rates;
    private List<Comment> comments;


    public Double addRate(Rate rate) {
        rates = rates.append(rate);
        rating = rates.toJavaStream()
                .mapToInt(Rate::getRateValue)
                .average()
                .orElse(rating);
        return rating;
    }

    public Comment addComment(Comment comment) {
       comments = comments.push(comment);
        return comment;
    }

}
