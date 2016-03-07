package com.github.peggybrown.speechrank.entity;

import javaslang.collection.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Conference {

    String id;
    String name;
    List<Presentation> presentations;
}
