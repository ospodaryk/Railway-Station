package com.example.railwaystation.classes.Game;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LevelReaderTest {

    @Test
    void loadLevel() {
        URL assetFolder = LevelReaderTest.class.getClassLoader().getResource("com/example/railwaystation/assets");
//        GameLevel lvl = LevelReader.loadLevel(assetFolder.getPath() + "/levels/sample.json");
    }

}