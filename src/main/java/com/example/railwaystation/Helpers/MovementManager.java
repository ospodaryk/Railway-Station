package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Game.GameLevel;
import com.example.railwaystation.Models.QueuePoligon;

import java.util.Iterator;

public class MovementManager {


    public static void findNextPos(GameLevel lvl, QueuePoligon pol, User u, QueueManager man, Iterator<User> ui) throws Exception {
        var indicator = u.move_next_step();
        var usr = u.getPosition();

        var res_pos = new Coordinates(usr.getX(), usr.getY());
        if (!indicator) {
            pol.getQueue().addUser(u);
            man.addUser(pol, u);
            ui.remove();
        }
    }

}
