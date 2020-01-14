package com.github.peggybrown.speechrank.gateway;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

public class ImporterTest {

    @Test
    public void testImport() {
        Config config = ConfigFactory.load();
        new Importer(config.getString("apiKey"))
            .importBoilingFrogs2019().forEach(System.out::println);
    }

}
