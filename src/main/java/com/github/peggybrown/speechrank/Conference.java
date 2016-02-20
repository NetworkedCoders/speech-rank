package com.github.peggybrown.speechrank;


import javaslang.collection.List;
import lombok.Value;

@Value
public class Conference {

    Long id;
    String name;
    List<Presentation> presentations;
}
