package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.entity.Conference;
import com.github.peggybrown.speechrank.gateway.Importer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InMemoryConferencesRepositoryTest {
    private InMemoryConferencesRepository conferencesRepository;
    private Importer importerMock;

    @Before
    public void setUp() {
        importerMock = mock(Importer.class);
        when(importerMock.importDevConf2017()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importDevConf2019()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importBoilingFrogs2018()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importBoilingFrogs2019()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importScalar2019()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importConfitura2018()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importConfitura2019()).thenReturn(javaslang.collection.List.empty());
        when(importerMock.importFromYouTubePlaylist(any())).thenReturn(javaslang.collection.List.empty());
        conferencesRepository = new InMemoryConferencesRepository(importerMock);
    }

    @Test
    public void importAllConferencesShouldAddAnyConference() {
        //when:
        conferencesRepository.importAllConferences();
        List<Conference> conferences = conferencesRepository.getConferences();

        //then:
        assertFalse(conferences.isEmpty());
    }

    @Test
    public void addAndGetConference() {
        //given:
        String conferenceName = "conference name";
        ConferenceImportDto conference = new ConferenceImportDto();
        conference.setYear("1990");
        conference.setName(conferenceName);
        conference.setPlaylistId("https://www.test.com");

        //when:
        conferencesRepository.importConference(conference);
        List<Conference> conferences = conferencesRepository.getConferences();
        boolean doesRepositoryContainConference =
            conferences.stream()
                .anyMatch(conf ->
                conferenceName.equals(conf.getName()));

        //then:
        assertTrue(doesRepositoryContainConference);
    }

    @Test
    public void addRateToPresentationTest() {
        //TODO: check if after addRate(Rate rate) the presentation has this rate
    }

    @Test
    public void addCommentToPresentationTest() {
        //TODO: check if after addComment(Comment comment) the presentation has this comment
    }
}
