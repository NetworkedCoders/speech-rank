package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.entity.Conference;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InMemoryConferencesRepositoryTest {
    private InMemoryConferencesRepository conferencesRepository;

    @Before
    public void setUp() {
        conferencesRepository = new InMemoryConferencesRepository();
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
        conference.setPlaylistLink("https://www.test.com");

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
