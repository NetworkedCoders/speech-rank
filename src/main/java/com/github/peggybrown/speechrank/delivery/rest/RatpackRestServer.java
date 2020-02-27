package com.github.peggybrown.speechrank.delivery.rest;

import com.github.peggybrown.speechrank.ConferencesModule;
import com.github.peggybrown.speechrank.ConferencesRepository;
import com.github.peggybrown.speechrank.dto.ConferenceImportDto;
import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Rate;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

import static ratpack.jackson.Jackson.json;

public class RatpackRestServer {

    private String options;

    public RatpackRestServer(final String options) {
        this.options = options;
    }

    public void start() throws Exception {
        RatpackServer.start(server -> server
            .registry(Guice.registry(bindings -> bindings.module(ConferencesModule.class)))
            .handlers(chain -> {
                    chain
                        .all(new CORSHandler())
                        .get("", ctx -> ctx.render(options))
                        .post("api/rating", ctx ->
                            ctx.parse(Rate.class).then(rate -> {
                                ctx.get(ConferencesRepository.class).add(rate);
                                ctx.render(json(rate.getRate()));
                            }))
                        .post("api/comment", ctx ->
                            ctx.parse(Comment.class).then(comment -> {
                                ctx.get(ConferencesRepository.class).add(comment);
                                ctx.render(json(comment.getComment()));
                            }))
                        .post("api/import", ctx ->
                            ctx.parse(ConferenceImportDto.class).then(conf -> {
                                ConferencesRepository conferencesRepo = ctx.get(ConferencesRepository.class);
                                String id = conferencesRepo.importConference(conf);
                                ctx.render(json(conferencesRepo.getConference(id)));
                            }))
                        .get("api/conferences", ctx -> ctx.render(json(ctx.get(ConferencesRepository.class).getYears())))
                        .get("api/conference/:id", ctx ->
                            ctx.render(json(ctx.get(ConferencesRepository.class).getConference(ctx.getPathTokens().get("id")))));
                }
            ));
    }

}
