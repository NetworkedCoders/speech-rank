package com.github.peggybrown.speechrank.entity;

import javaslang.collection.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class Year {
    private final String year;
    @JsonIgnore
    private List<Conference> conferences = List.empty();

    public Void addConference(Conference conf) {
        conferences = conferences.append(conf);
        //TODO what to return here?
        return null;
    }


}
