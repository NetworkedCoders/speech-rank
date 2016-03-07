package com.github.peggybrown.speechrank.delivery.rest;

import lombok.extern.slf4j.Slf4j;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;

@Slf4j
public class CORSHandler implements Handler {

    @Override
    public void handle(final Context ctx) throws Exception {
        log.info("Adding CORS headers");
        final MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        ctx.next();
    }

}
