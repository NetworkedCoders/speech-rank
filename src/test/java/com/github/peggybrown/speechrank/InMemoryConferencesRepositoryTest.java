package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Conference;
import com.github.peggybrown.speechrank.entity.Presentation;
import com.github.peggybrown.speechrank.entity.Rate;
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

    private ConferenceImportDto prepareConference() {
        String conferenceName = "conference name";
        ConferenceImportDto conference = new ConferenceImportDto();
        conference.setYear("1990");
        conference.setName(conferenceName);
        conference.setPlaylistId("https://www.test.com");
        return conference;
    }

    @Test
    public void addAndGetConference() {
        //given:
        ConferenceImportDto conference = prepareConference();

        //when:
        conferencesRepository.importConference(conference);
        List<Conference> conferences = conferencesRepository.getConferences();
        boolean doesRepositoryContainConference =
            conferences.stream()
                .anyMatch(conf ->
                conference.getName().equals(conf.getName()));

        //then:
        assertTrue(doesRepositoryContainConference);
    }

    @Test
    public void addRateToPresentationTest() {
        //given:
        Importer.VideoData videoData = new Importer.VideoData("videoId", "videoTitle", "videoDescription");
        ConferenceImportDto conference = prepareConference();
        when(importerMock.importFromYouTubePlaylist(conference.getPlaylistId()))
            .thenReturn(javaslang.collection.List.of(videoData));
        conferencesRepository = new InMemoryConferencesRepository(importerMock);
        conferencesRepository.importConference(conference);
        Presentation presentation = getPresentation(conference.getName(), videoData.getTitle());
        Rate rate = prepareRate(presentation);

        //when:
        conferencesRepository.add(rate);
        boolean doesPresentationContainsRate =
            getPresentation(conference.getName(), videoData.getTitle())
                .getRates()
                .toJavaStream()
                .anyMatch(streamRate ->
                    rate.getRate() == streamRate.getRate()
                        && rate.getUserId().equals(streamRate.getUserId()));

        //then:
        assertTrue(doesPresentationContainsRate);
    }

    private Rate prepareRate(Presentation presentation) {
        Rate rate = new Rate();
        rate.setUserId("user id");
        rate.setRate(4);
        rate.setPresentationId(presentation.getId());
        return rate;
    }

    @Test
    public void addCommentToPresentationTest() {
        //given:
        Importer.VideoData videoData = new Importer.VideoData("videoId", "videoTitle", "videoDescription");
        ConferenceImportDto conference = prepareConference();
        when(importerMock.importFromYouTubePlaylist(conference.getPlaylistId()))
            .thenReturn(javaslang.collection.List.of(videoData));
        conferencesRepository = new InMemoryConferencesRepository(importerMock);
        conferencesRepository.importConference(conference);
        Presentation presentation = getPresentation(conference.getName(), videoData.getTitle());
        Comment comment = prepareComment(presentation);

        //when:
        conferencesRepository.add(comment);
        boolean doesPresentationContainsComment =
            getPresentation(conference.getName(), videoData.getTitle())
                .getComments()
                .toJavaStream()
                .anyMatch(comm -> comment.getComment().equals(comm.getComment()));

        //then:
        assertTrue(doesPresentationContainsComment);
    }

    private Comment prepareComment(Presentation presentation) {
        Comment comment = new Comment();
        comment.setComment("comment content");
        comment.setPresentationId(presentation.getId());
        comment.setUserId("user id");
        comment.setUsername("user name");
        return comment;
    }

    private Presentation getPresentation(String conferenceName, String videoDataTitle) {
        return conferencesRepository.getConferences()
                .stream()
                .filter(conf -> conferenceName.equals(conf.getName()))
                .map(Conference::getPresentations)
                .flatMap(javaslang.collection.List::toJavaStream)
                .filter(prez -> videoDataTitle.equals(prez.getTitle()))
                .findAny()
                .get();
    }
}
