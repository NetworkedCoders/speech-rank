package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.dto.ConferenceDto;
import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.dto.YearDto;
import com.github.peggybrown.speechrank.entity.*;
import com.github.peggybrown.speechrank.gateway.Importer;
import com.google.inject.Inject;
import javaslang.collection.List;
import lombok.extern.java.Log;

import java.util.UUID;
import java.util.stream.Collectors;

@Log
public class InMemoryConferencesRepository implements ConferencesRepository {

    private Importer importer;
    private List<Conference> conferences;
    private List<Year> years;

    @Inject
    InMemoryConferencesRepository(Importer importer) {
        this.importer = importer;
        conferences = List.empty();
        initYears();
        importAllConferences();
    }

    @Override
    public void add(Rate rate) {
        log.info("Rate added: " + rate.toString());
        Presentation presentation = conferences
            .flatMap(Conference::getPresentations)
            .find(prez -> prez.getId().equals(rate.getPresentationId())).get();
        presentation.addRate(rate);
    }

    @Override
    public void add(Comment comment) {
        log.info("Comment added: " + comment.toString());
        conferences
            .flatMap(Conference::getPresentations)
            .find(prez -> prez.getId().equals(comment.getPresentationId()))
            .map(prez -> prez.addComment(comment));

    }

    private void add(String year, Conference conf) {
        log.info("Conference added: " + conf.toString());
        conferences = conferences.append(conf);
        years.filter(y -> y.getYear().equals(year))
            .map(y -> y.addConference(conf));
    }

    @Override
    public java.util.List<Conference> getConferences() {
        return conferences.toJavaList();
    }

    @Override
    public java.util.List<YearDto> getYears() {
        return years.toJavaStream().map(YearDto::new).collect(Collectors.toList());
    }

    @Override
    public ConferenceDto getConference(String id) {
        //TODO what to return whe conference not found?
        return new ConferenceDto(conferences.find(conf -> conf.getId().equals(id)).get());
    }

    @Override
    public void importAllConferences() {
        add("2017", new Conference("11", "DevConf", importer.importDevConf2017().map(Presentation::new)));
        add("2019", new Conference("12", "DevConf", importer.importDevConf2019().map(Presentation::new)));
        add("2018", new Conference("21", "Boiling Frogs", importer.importBoilingFrogs2018().map(Presentation::new)));
        add("2019", new Conference("31", "Boiling Frogs", importer.importBoilingFrogs2019().map(Presentation::new)));
        add("2019", new Conference("41", "Scalar", importer.importScalar2019().map(Presentation::new)));
        add("2018", new Conference("51", "Confitura", importer.importConfitura2018().map(Presentation::new)));
        add("2019", new Conference("51", "Confitura", importer.importConfitura2019().map(Presentation::new)));
    }

    private void initYears() {
        years = List.of(new Year("2019"), new Year("2018"), new Year("2017"));
    }

    @Override
    public String importConference(ConferenceImportDto conf) {
        String id = UUID.randomUUID().toString();
        Conference conference = new Conference(id, conf.getName(), importer.importFromYouTubePlaylist(conf.getPlaylistId()).map(
            Presentation::new));
        add(conf.getYear(), conference);
        return id;
    }
}
