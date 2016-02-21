package com.github.peggybrown.speechrank;

import javaslang.collection.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presentation {

    private String id;
    private String title;
    private String link;
    private Double rating;
    private List<Rate> rates;
    private List<Comment> comments;


    public Double addRate(Rate rate) {
        rates.push(rate);
        rating = rates.toJavaStream()
                .mapToInt(Rate::getRateValue)
                .average()
                .orElse(rating);
        return rating;
    }

    public Comment addComment(Comment comment) {
        comments.push(comment);
        return comment;
    }

}
