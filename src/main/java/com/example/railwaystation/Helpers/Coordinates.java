package com.example.railwaystation.Helpers;

public class Coordinates {

    private double x;
    private double y;


    public Coordinates(){
        x = 0;
        y = 0;
    }


    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates copy(){
        return new Coordinates(this.x, this.y);
    }
    public Coordinates add(Coordinates b){
        return new Coordinates(x + b.x, y + b.y);
    }
    public boolean compareStartAlg(Coordinates other) {
        return this.x == other.y && this.y == other.x;
    }

}
