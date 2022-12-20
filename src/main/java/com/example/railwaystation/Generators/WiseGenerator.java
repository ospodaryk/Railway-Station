package com.example.railwaystation.Generators;

import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Rendering.ResourceManagerUser;
import com.example.railwaystation.Game.Game;
import com.example.railwaystation.Models.Door;
import com.example.railwaystation.Models.UserFiles.UserType;

import java.io.IOException;
import java.util.SplittableRandom;

public class WiseGenerator implements Generator {

    private Door door;
    private final UserDataGenerator dataGenerator;
    private float probability;
    private SplittableRandom randomizer;
    public WiseGenerator(Door door) throws IOException {
        this.probability = 100;
        this.randomizer = new SplittableRandom();
        this.door = door;
        dataGenerator = UserDataGenerator.getInstance();
    }

    @Override
    public User generateUser() {
        checkProbability();
        if(this.door.isOpen()){
            if(randomizer.nextInt(1, 101) <= probability){
                var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
                user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                        dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(), dataGenerator.generateTickets(), door.getPosition(), this.door);
                if(user.getType() == UserType.ORDINARY){
                    user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
                }
                return user;
            }
        }
        return null;
    }
    private void checkProbability(){
        if(isBetween(Game.getUsersCount(),0,(int) (Game.getMaxUserCount() * 0.25))){
            probability = 100;
        } else if(isBetween(Game.getUsersCount(),(int) (Game.getMaxUserCount() * 0.25),(int) (Game.getMaxUserCount() * 0.5))){
            probability = 75;
        } else if(isBetween(Game.getUsersCount(),(int) (Game.getMaxUserCount() * 0.5),(int)(Game.getMaxUserCount() * 0.75))){
            probability = 50;
        }  else{
            probability = 25;
        }
    }
    private  boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}