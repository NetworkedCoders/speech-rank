package com.github.peggybrown.speechrank.delivery.rest;

import com.github.peggybrown.speechrank.ConferencesRepository;
import com.github.peggybrown.speechrank.Main;
import com.github.peggybrown.speechrank.dto.YearDto;
import com.github.peggybrown.speechrank.entity.Conference;
import com.github.peggybrown.speechrank.entity.Presentation;
import com.github.peggybrown.speechrank.entity.Year;
import javaslang.collection.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ratpack.guice.BindingsImposition;
import ratpack.http.client.ReceivedResponse;
import ratpack.impose.ImpositionsSpec;
import ratpack.test.MainClassApplicationUnderTest;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RatpackRestServerTest {

    private static final int OK_STATUS_CODE = 200;
    private static MainClassApplicationUnderTest testedApp;
    private static ConferencesRepository conferencesRepository = mock(ConferencesRepository.class);

    @BeforeClass
    public static void setUp(){
        testedApp = new MainClassApplicationUnderTest(Main.class) {
            @Override
            protected void addImpositions(ImpositionsSpec impositions) {
                impositions.add(BindingsImposition.of(bindingsSpec -> bindingsSpec.bindInstance(ConferencesRepository.class, conferencesRepository)));
            }
        };
    }

    @AfterClass
    public static void tearDown(){
        testedApp.close();
    }

    @Test
    public void shouldReturnYearsWithCorrespondingConferences() {
        Year year = new Year("5050");
        List<Presentation> presentations = List.of(new Presentation("id", "title", "link", 2.09, List.empty(), List.empty()));
        year.addConference(new Conference("1", "boiled frog", presentations));
        when(conferencesRepository.getYears()).thenReturn(singletonList(new YearDto(year)));

        ReceivedResponse response = get("/api/conferences");

        verify(conferencesRepository).getYears();
        assertThat(response.getStatusCode()) //TODO: add custom assertion for `ReceivedResponse`
            .isEqualTo(OK_STATUS_CODE);
        //TODO: check response body
    }

    private ReceivedResponse get(String conferenceEndpoint) {
        return testedApp.getHttpClient()
            .get(conferenceEndpoint);
    }
}
