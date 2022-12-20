package com.example.railwaystation.Generators;


import java.io.IOException;
import java.util.SplittableRandom;

import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Rendering.ResourceManagerUser;
import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.Door;
import com.example.railwaystation.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.Models.UserFiles.UserType;


public class ConstUserGenerator implements Generator{
    SplittableRandom randomizer;
    Door door;
    private final UserDataGenerator dataGenerator;
    public ConstUserGenerator(Door door) throws IOException {
        this.door = door;
        this.randomizer = new SplittableRandom();
        dataGenerator = UserDataGenerator.getInstance();
    }

    @Override
    public User generateUser() {
        if (this.door.isOpen()){
            var typeIndex = randomizer.nextInt(UserType.values().length);
            var user = PrototypeRegistry.getPrototype(UserType.values()[randomizer.nextInt(UserType.values().length)]).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(), this.door);
            if(user.getType() == UserType.ORDINARY){
                user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
            }
            return user;
        } return null;
    }
}
