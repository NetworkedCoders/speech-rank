package com.github.peggybrown.speechrank;

import lombok.Data;

@Data
public class Rate {
    String userId;
    String presentationId;
    int rateValue;
}
