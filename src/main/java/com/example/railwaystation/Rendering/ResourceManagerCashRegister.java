package com.example.railwaystation.Rendering;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ResourceManagerCashRegister {
    private static final Map<String, Image> sprites = new HashMap<>();
    public static Image getSprite(String key){
        return sprites.get(key);
    }
    public static void setSprite(String key, Image sprite){
        sprites.put(key, sprite);
    }
}
