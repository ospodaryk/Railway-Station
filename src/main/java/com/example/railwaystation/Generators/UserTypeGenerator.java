package com.example.railwaystation.Generators;

import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Rendering.ResourceManagerUser;
import com.example.railwaystation.Interfaces.Generator;
import com.example.railwaystation.Models.Door;
import com.example.railwaystation.Models.UserFiles.PrototypeRegistry;
import com.example.railwaystation.Models.UserFiles.UserType;


import java.io.IOException;
import java.util.SplittableRandom;

public class UserTypeGenerator implements Generator {

    private Door door;
    private final UserDataGenerator dataGenerator;
    private UserType userType;
    private SplittableRandom randomizer;

    public UserTypeGenerator(Door door, UserType type) throws IOException {
        this.door = door;
        this.userType = type;
        randomizer = new SplittableRandom();
        dataGenerator = UserDataGenerator.getInstance();
    }


    @Override
    public User generateUser() {
        if (this.door.isOpen()) {
            var user = PrototypeRegistry.getPrototype(userType).userClone();
            user.setPersonInfo(dataGenerator.generateName(), dataGenerator.generateLastName(), dataGenerator.generateAge(),
                    dataGenerator.generatePassportId(), dataGenerator.generatePhoneNumber(),dataGenerator.generateTickets(), door.getPosition(), this.door);
            if(user.getType() == UserType.ORDINARY){
                user.setSprite(ResourceManagerUser.getSprite("ordinary_"+randomizer.nextInt(1,36)));
            }
            return user;
        }
        return null;
    }
}