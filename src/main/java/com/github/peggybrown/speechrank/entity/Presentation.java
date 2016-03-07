package com.github.peggybrown.speechrank.entity;

import com.github.peggybrown.speechrank.gateway.Importer;
import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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

    public Presentation(Importer.VideoData videoData) {
        id = UUID.randomUUID().toString();
        title = videoData.getTitle();
        link = "https://youtube.com/v/" + videoData.getVideoId();
        rating = 0.0;
        rates = List.empty();
        comments = List.empty();
    }


    public Double addRate(Rate rate) {
        rates = rates.append(rate);
        rating = rates.toJavaStream()
                .mapToInt(Rate::getRate)
                .average()
                .orElse(rating);
        return rating;
    }

    public Comment addComment(Comment comment) {
        comments = comments.push(comment);
        return comment;
    }

}
