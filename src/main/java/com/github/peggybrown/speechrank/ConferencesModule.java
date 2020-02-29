package com.github.peggybrown.speechrank;

import com.google.inject.AbstractModule;

public class ConferencesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConferencesRepository.class)
            .to(InMemoryConferencesRepository.class)
            .asEagerSingleton();
    }

}
