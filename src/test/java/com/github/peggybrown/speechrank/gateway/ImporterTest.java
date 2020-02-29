package com.github.peggybrown.speechrank.gateway;

import org.junit.Test;

public class ImporterTest {

    @Test
    public void testImport() {
        new Importer().importBoilingFrogs2019().forEach(System.out::println);
    }

}
