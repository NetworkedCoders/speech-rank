package com.github.peggybrown.speechrank;


import javaslang.collection.List;
import lombok.Value;

@Value
public class Conference {

    String id;
    String name;
    List<Presentation> presentations;
}
