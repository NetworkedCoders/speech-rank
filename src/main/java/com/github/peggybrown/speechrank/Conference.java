package com.github.peggybrown.speechrank;

import javaslang.collection.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conference {

    String id;
    String name;
    List<Presentation> presentations;
}
