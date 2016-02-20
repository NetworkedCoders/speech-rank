package com.github.peggybrown.speechrank;


import javaslang.collection.List;
import lombok.Value;

@Value
public class Presentation {

    Long id;
    String title;
    String link;
    Float rate;
    List<Comment> comments;


}
