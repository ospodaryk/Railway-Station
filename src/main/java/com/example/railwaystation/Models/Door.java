package com.example.railwaystation.Models;

import com.example.railwaystation.Helpers.Coordinates;
import javafx.scene.image.Image;

import java.util.Objects;

public class Door extends GameObject {

    private boolean _open;
    public Door(){
        _open = true;
    }

    @Override
    public String toString() {
        return "Door{" +
                "_open=" + _open +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Door door)) return false;
        return _open == door._open;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPosition());
    }

    public Door(Coordinates position, double width, double height, Image sprite, float angle, boolean _open) {
        super(position, width, height, sprite, angle);
        this._open = _open;
    }

    public boolean isOpen() {
        return _open;
    }
    public void open(){
        this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/door1.png"));
        setState(true);
    }
    public void close(){
        this.setSprite(new Image("file:src/main/resources/com/example/railwaystation/img/icons/nodoor1.png"));
        setState(false);
    }
    public void setState(boolean flag){
        _open = flag;
    }

}
