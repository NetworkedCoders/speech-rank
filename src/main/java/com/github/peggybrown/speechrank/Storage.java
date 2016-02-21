package com.github.peggybrown.speechrank;

import javaslang.collection.List;
import lombok.extern.java.Log;

@Log
public class Storage {
    private List<Conference> conferences = List.empty();

    public void add(Rate rate) {
        log.info("Rate added");
        conferences.toStream()
                .flatMap(Conference::getPresentations)
                .find(prez -> prez.getId().equals(rate.getPresentationId()))
                .map(prez -> prez.addRate(rate));
    }

    public void add(Comment comment) {
        log.info("Comment added");
        conferences.toStream()
                .flatMap(Conference::getPresentations)
                .find(prez -> prez.getId().equals(comment.getPresentationId()))
                .map(prez -> prez.addComment(comment));

    }

    public void add(Conference conf) {
        log.info("Conference added" + conf.toString());
        conferences.push(conf);
    }
}
