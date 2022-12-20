package com.example.railwaystation.Helpers;

import com.example.railwaystation.Models.OurQueue;
import com.example.railwaystation.Models.UserFiles.User;
import com.example.railwaystation.Game.GameLevel;
import com.example.railwaystation.Models.QueuePoligon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QueueManager {


    private GameLevel level;
    private QueuePoligon poligon;

    public QueueManager(GameLevel level) {
        this.level = level;
    }

    public void addUser(QueuePoligon poligon, User user) {
        this.poligon = poligon;

        moveNewUser(user,poligon);
    }

    private void findPolygonQueue(OurQueue queue){
        for (var p: level.get_poligons()) {
            if(p.getQueue() == queue){
                poligon = p;
                return;
            }
        }

        poligon = null;
    }

    // Moves all users in the QueuePolygon  one step forward
    public void moveUsersInQueuePolygon(OurQueue queue) {
        findPolygonQueue(queue);
        if(poligon == null)
            return;
        moveAllUsers(poligon);
    }

    private void moveAllUsers(QueuePoligon poligon) {
        var users = poligon.getQueue().getUsers().stream().toList();
        var cells = poligon.getQueueCells().stream().toList();
        double cellX = 0;
        double cellY = 0;

        for (int i = 0; i < users.size(); i++) {
            if(i < cells.size()) {
                cellX = cells.get(i).getPosition().getX();
                cellY = cells.get(i).getPosition().getY();

                users.get(i).getPosition().setXY(cellX, cellY);
            }else {
                users.get(users.size() - 1).getPosition().setXY(cellX, cellY);
                return;
            }
        }
    }

    public void moveNewUser(User newUser, QueuePoligon poligon) {
        moveAllUsers(poligon);
    }

    //Клієнт має можливість обирати одну з кас
    // за принципом вибору тієї, в черзі до якої
    // є найменша кількість людей. Якщо кількість
    // людей в черзі є однаковою, то клієнт обирає
    // ту, яка є найближчою.
    public QueuePoligon getCorrectQueue(User usr, List<QueuePoligon> lst) {

        var ourq = level.get_cashRegistersList()
                .stream().filter(p -> p.isOpen())
                .map(p -> p.getOurQueue()).collect(Collectors.toList());

        var new_lst = lst.stream()
                .filter(p -> ourq.contains(p.getQueue()))
                .collect(Collectors.toList());

        var res = new_lst
                .stream()
                .sorted(Comparator.comparingInt(QueuePoligon::getPotentialCount)).toList();

        if (res.stream().allMatch(p -> p.getPotentialCount() == res.get(0).getPotentialCount())) {
            return res
                    .stream()
                    .sorted(Comparator.comparingInt(o -> DistanceHelper.calcDistance(usr, o))).toList()
                    .get(0);
        }

        return res.get(0);
    }
}
