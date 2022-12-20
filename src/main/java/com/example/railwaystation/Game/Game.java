package com.example.railwaystation.Game;

import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.OurQueue;
import com.example.railwaystation.Models.UserFiles.Priority;
import com.example.railwaystation.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Models.UserFiles.UserType;
import com.example.railwaystation.MovingLogic.MoveAlgo.Node;
import com.example.railwaystation.Rendering.ResourceManagerUser;
import com.example.railwaystation.Models.Door;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Game {


    private static int usersCount;
    private static int maxUserCount;


    private static List<Generator> _generators;

    public static HashMap<Door, HashMap<Coordinates, List<Node>>> getResolver() {
        return resolver;
    }

    public static void setResolver(HashMap<Door, HashMap<Coordinates, List<Node>>> resolver) {
        Game.resolver = resolver;
    }

    private static HashMap<Door, HashMap<Coordinates, List<Node>>> resolver;

    private static OurQueue queueToShow = null;

    private static Game _game;

    private static Collection<GameLevel> _levels;

    public static GameLevel getCurrentLevel() {
        return currentLevel;
    }

    public static GameLevel currentLevel;


    public static GameLevel get_currentLevel(){
        return currentLevel;
    }
    public static int get_currentLevelCell_Size(){
        return currentLevel.getCell_size();
    }
    public static void setGenerators(List<Generator> generators) {
        _generators = generators;
    }
    // Load all needed resources and populate UserPrototypeManger with resources
    public static void Init(){
        // Sprites
        AssetsReader.loadAssets();
        // Levels
        _levels = LevelReader.loadLevels();
        // Users
        PrototypeRegistry.setPrototype(UserType.ORDINARY,new User(ResourceManagerUser.getSprite("ordinary"),UserType.ORDINARY, Priority.LOW));
        PrototypeRegistry.setPrototype(UserType.PREGNANT,new User(ResourceManagerUser.getSprite("pregnant"),UserType.PREGNANT,Priority.MEDIUM));
        PrototypeRegistry.setPrototype(UserType.DISABLED,new User(ResourceManagerUser.getSprite("disabled"),UserType.DISABLED,Priority.HIGH));
    }

    public static void showQueueDetails(OurQueue queue){
        Game.queueToShow = queue;
    }
    public static String getShowQueueDetailsString(OurQueue queue){
        Game.queueToShow = queue;
        return Game.queueToShow.toString();

    }
    public static int getMaxUserCount() {
        return maxUserCount;
    }

    public static void setMaxUserCount(int maxUserCount) {
        Game.maxUserCount = maxUserCount;
    }

    public static int getUsersCount() {
        return usersCount;
    }

    public static void setUsersCount(int usersCount) {
        Game.usersCount = usersCount;
    }
    public static OurQueue getQueueToShow() {
        return queueToShow;
    }

    public static void setQueueToShow(OurQueue queueToShow) {
        Game.queueToShow = queueToShow;
    }

    public static void setCurrentLevel(GameLevel currentLevel) {
        Game.currentLevel = currentLevel;
    }
}
