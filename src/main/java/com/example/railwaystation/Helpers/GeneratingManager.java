package com.example.railwaystation.Helpers;

import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Game.GameLevel;
import com.example.railwaystation.Models.Door;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Class which is supposed to handle generating and control number of users in level.
 */
public class GeneratingManager {

    private final GameLevel _level;
    private final List<Generator> _userSources;
    private boolean overcrowded = false;

    /**
     * @param level - level to handle
     * @param userSources - collection of user generators
     */
    public GeneratingManager(GameLevel level, List<Generator> userSources){
        if(level == null || userSources == null)
            throw new IllegalArgumentException("Neither parameter is allowed to be null!");
        this._level = level;
        this._userSources = userSources;
    }


    /**
     * Count all existing users in the level
     * @return Number of users in station
     */
    public int countUsersInStation(){

        int counter = countUsersInQueues();
        counter += _level.get_movingUsers().size();
        return counter;
    }

    /**
     * Count users that is currently in a queue
     * @return Number of users
     */
    public int countUsersInQueues(){
        int counter = 0;
        for (var poligon : _level.get_poligons())
            counter += poligon.getQueue().size();

        return counter;
    }

    /**
     * Collect users from all generators and add them to the collection of
     * moving users of the level.
     * @return Users from all generators without nulls;
     */
    public List<User> collectUsers(int freeNumber){
        // collect users from each generator
        var newUsers = _userSources.stream()
                .sorted(Comparator.comparingDouble(g -> Math.random()))
                .map(Generator::generateUser)
                .filter(Objects::nonNull)
                .limit(freeNumber)
                .toList();

        _level.get_movingUsers().addAll(newUsers);

        return newUsers;
    }

    public boolean isCrowded(int maxUserCount){
        int userNumber = this.countUsersInStation();
        if(userNumber >= maxUserCount){
            overcrowded = true;
        }
        else if (overcrowded && userNumber <= (int)maxUserCount * 0.7 ) {
            overcrowded = false;
        }
        return overcrowded;
    }

    /**
     * Will close all doors in the level.
     */

    public void closeDoors(){
        _level.get_doorsList().forEach(Door::close);
    }
    public void openDoors(){
        _level.get_doorsList().forEach(Door::open);
    }
}


