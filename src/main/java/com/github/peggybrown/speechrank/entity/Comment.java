package com.github.peggybrown.speechrank.entity;

import lombok.Data;

@Data
public class Comment {

    String presentationId;
    String userId;
    String username;
    String comment;
}
