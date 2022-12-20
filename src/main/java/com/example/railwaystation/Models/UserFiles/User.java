package com.example.railwaystation.Models.UserFiles;

import com.example.railwaystation.Models.GameObject;
import com.example.railwaystation.Models.QueuePoligon;
import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.MovingLogic.MoveAlgo.Node;
import com.example.railwaystation.Models.Door;
import com.example.railwaystation.Models.TicketFIles.Ticket;
import javafx.scene.image.Image;

import java.util.Iterator;
import java.util.List;


public class User extends GameObject {
    private UserType type;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    private UserInfo userInfo;
    private List<Ticket> tickets;
    public Priority priority;

    public User(Image sprite, UserType type, Priority priority) {
        this.type = type;
        this.priority = priority;
        this.setSprite(sprite);
        this.target = null;
    }

    public User userClone() {
        return new User(this.getSprite(), this.type, this.priority);
    }

    public void setPersonInfo(String name, String surname, int age, String passportId, String phoneNumber, List<Ticket> tickets, Coordinates coordinates, Door birthPlace) {
        this.userInfo = new UserInfo(name, surname, age, passportId, phoneNumber);
        this.tickets = tickets;
        this.setPosition(coordinates);
        this.birthPlace = birthPlace;
    }

    public UserType getType() {
        return type;
    }

    public Priority getPriority() {
        return priority;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }


    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("   ").append(type).append("    ").append(refact(userInfo.toString(), 35)).append(" ");
        tmp.append(tickets.get(0));
        for (int i = 1; i < tickets.size(); i++) {
            tmp.append(" ".repeat(79)).append(tickets.get(i));
        }
        tmp.append("-".repeat(136)).append("\n");
        return tmp.toString();
    }

    public String refact(String user, int len) {
        int a = userInfo.toString().length();
        len -= a;
        String add = "";

        for (int i = 0; i < len / 2; i++) {
            add += " ";
        }
        if ((len % 2 == 1) && (len > 0)) {
            return add + user + add + " ";
        } else {
            return add + user + add;
        }
    }

    public List<Node> get_path() {
        return path;
    }

    public Door get_birth_place() {
        return birthPlace;
    }

    public boolean move_next_step() {
        if (this.path.size() > this.pathIndex) {
            var el = this.path.get(this.pathIndex);
            setPosition(new Coordinates(el.getCol(), el.getRow()));
            this.pathIndex++;
            return true;
        }

        return false;
    }

    public void set_target(QueuePoligon _target) {
        this.target = _target;
    }
    public void set_path(List<Node> _path) {
        this.path = _path;
        this._it = this.path.iterator();
    }

    public QueuePoligon get_target() {
        return target;
    }
    private Door birthPlace = null;
    private int pathIndex = 1;
    private Iterator<Node> _it;
    private List<Node> path = null;
    private QueuePoligon target = null;
}


