package com.example.railwaystation.Interfaces;

import com.example.railwaystation.Helpers.Coordinates;

import javafx.scene.image.Image;

public interface Rendering {
    public void drawImage(Coordinates pos, double width, double height, double angle, Image sprite);
    public void drawGrid(double width, double height, double step_x, double step_y);
    public void clearCtx();
}
