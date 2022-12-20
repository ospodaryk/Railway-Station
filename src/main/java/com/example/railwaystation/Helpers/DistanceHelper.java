package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Models.QueuePoligon;

public class DistanceHelper {
    public static int calcDistance(User usr, QueuePoligon qp) {
        var upos = usr.getPosition();
        var qpos = qp.getQueueTailCoordinates().getPosition();
        return (int)Math.sqrt(Math.pow(qpos.getX() - upos.getX(), 2) + Math.pow(qpos.getY() - upos.getY(), 2));
    }
}
