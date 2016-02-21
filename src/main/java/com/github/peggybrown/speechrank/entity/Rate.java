package com.github.peggybrown.speechrank.entity;

import lombok.Data;

@Data
public class Rate {
    String userId;
    String presentationId;
    int rate;
}
