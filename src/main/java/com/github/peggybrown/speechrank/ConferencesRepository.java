package com.github.peggybrown.speechrank;

import java.util.stream.Collectors;

import javaslang.collection.List;

import lombok.extern.java.Log;

import com.github.peggybrown.speechrank.dto.ConferenceDto;
import com.github.peggybrown.speechrank.dto.YearDto;
import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Conference;
import com.github.peggybrown.speechrank.entity.Presentation;
import com.github.peggybrown.speechrank.entity.Rate;
import com.github.peggybrown.speechrank.entity.Year;
import com.github.peggybrown.speechrank.gateway.Importer;

@Log
public class ConferencesRepository {

    private Importer importer = new Importer();
    private List<Conference> conferences;
    private List<Year> years;

    public ConferencesRepository() {
        conferences = List.empty();
        initYears();
    }

    public void add(Rate rate) {
        log.info("Rate added: " + rate.toString());
        Presentation presentation = conferences
                .flatMap(Conference::getPresentations)
                .find(prez -> prez.getId().equals(rate.getPresentationId())).get();
        presentation.addRate(rate);
    }

    public void add(Comment comment) {
        log.info("Comment added: " + comment.toString());
        conferences
                .flatMap(Conference::getPresentations)
                .find(prez -> prez.getId().equals(comment.getPresentationId()))
                .map(prez -> prez.addComment(comment));

    }

    public void add(String year, Conference conf) {
        log.info("Conference added: " + conf.toString());
        conferences = conferences.append(conf);
        years.filter(y -> y.getYear().equals(year))
                .map(y -> y.addConference(conf));
    }

    public java.util.List<Conference> getConferences() {
        return conferences.toJavaList();
    }

    public java.util.List<YearDto> getYears() {
        return years.toJavaStream().map(YearDto::new).collect(Collectors.toList());
    }

    public ConferenceDto getConference(String id) {
        //TODO what to return whe conference not found?
        return new ConferenceDto(conferences.find(conf -> conf.getId().equals(id)).get());
    }

    public void importAllConferences() {
        add("2015", new Conference("11", "Confitura", importer.importConfitura2015().map(Presentation::new)));
        add("2014", new Conference("12", "Confitura", importer.importConfitura2014().map(Presentation::new)));
        add("2015", new Conference("21", "Devoxx", importer.importDevoxxUK2015().map(Presentation::new)));
        add("2014", new Conference("31", "Hacksummit", importer.importHackSummit2016().map(Presentation::new)));

    }

    public void initYears() {
        years = List.of(new Year("2014"), new Year("2015"));
    }

}
