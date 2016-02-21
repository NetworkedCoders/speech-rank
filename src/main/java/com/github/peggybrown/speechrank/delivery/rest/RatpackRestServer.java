package com.github.peggybrown.speechrank.delivery.rest;

import static ratpack.jackson.Jackson.json;

import com.github.peggybrown.speechrank.ConferencesRepository;
import com.github.peggybrown.speechrank.entity.Comment;
import com.github.peggybrown.speechrank.entity.Rate;

import ratpack.server.RatpackServer;

public class RatpackRestServer {

	private ConferencesRepository conferencesRepo;
	private String options;

	public RatpackRestServer(final ConferencesRepository conferencesRepository, final String options) {
		conferencesRepo = conferencesRepository;
		this.options = options;
	}

	public void start() throws Exception {
		RatpackServer.start(server -> server
				.handlers(chain -> chain
						.all(new CORSHandler())
						.get("", ctx -> ctx.render(options))
						.post("rating", ctx ->
								ctx.parse(Rate.class).then(rate -> {
									conferencesRepo.add(rate);
									ctx.render(json(rate.getRate()));
								}))
						.post("comment", ctx ->
								ctx.parse(Comment.class).then(comment -> {
									conferencesRepo.add(comment);
									ctx.render(json(comment.getComment()));
								}))
						.post("import", ctx -> {
							conferencesRepo.importAllConferences();
							ctx.render("OK");
						})
						.get("conferences", ctx -> ctx.render(json(conferencesRepo.getYears())))
						.get("conference/:id", ctx -> {
							ctx.render(json(conferencesRepo.getConference(ctx.getPathTokens().get("id"))));
						})));
	}

}
