package com.github.peggybrown.speechrank.entities;

import lombok.Data;

@Data
public class Rate {
    String userId;
    String presentationId;
    int rate;
}
