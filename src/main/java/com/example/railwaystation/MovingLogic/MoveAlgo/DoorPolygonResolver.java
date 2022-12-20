package com.example.railwaystation.MovingLogic.MoveAlgo;

import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.Game.GameLevel;
import com.example.railwaystation.Models.Door;

import java.util.HashMap;
import java.util.List;

public class DoorPolygonResolver {
    public static HashMap<Door, HashMap<Coordinates, List<Node>>> calculate(GameLevel gl) {
        var map = new HashMap<Door, HashMap<Coordinates, List<Node>>>();
        for (var door : gl.get_doorsList()) {
            var tmap = new HashMap<Coordinates, List<Node>>();
            for (var qe : gl.get_poligons()) {

                var res = AlgorithmResolver.resolvePath(
                        door.getPosition(),
                        qe.getQueueTailCoordinates().getPosition(),
                        gl);

                tmap.put(qe.getQueueTailCoordinates().getPosition(), res);
            }

            map.put(door, tmap);
        }

        return map;
    }
}
