package com.example.railwaystation.Rendering;

import com.example.railwaystation.Interfaces.Rendering;
import com.example.railwaystation.UI.CanvasManager.Camera2D;
import com.example.railwaystation.Helpers.Coordinates;
import com.example.railwaystation.Game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class CanvasRendering implements Rendering {
    private Canvas _canvas;
    private Camera2D _camera;

    public void set_camera(Camera2D _camera) {
        this._camera = _camera;
    }

    public CanvasRendering(Canvas canvas_1) {
        _canvas=canvas_1;
        _camera = new Camera2D();
    }

    @Override
    public void drawImage(Coordinates pos, double width, double height, double angle, Image sprite) {
        _canvas.getGraphicsContext2D().drawImage(
                sprite,
                pos.getX()*Game.get_currentLevelCell_Size() * _camera.get_zoom() - _camera.get_position().getX(),
                pos.getY()*Game.get_currentLevelCell_Size() * _camera.get_zoom() - _camera.get_position().getY(),
                Game.get_currentLevelCell_Size() * _camera.get_zoom(),
                Game.get_currentLevelCell_Size() * _camera.get_zoom()
        );
    }

    @Override
    public void drawGrid(double width, double height, double step_x, double step_y) {
        var ctx = _canvas.getGraphicsContext2D();
        ctx.setStroke(Color.BLUEVIOLET);
        double width_low = 0 - _camera.get_position().getX();
        double width_high = width*_camera.get_zoom() - _camera.get_position().getX();

        double height_low = 0 - _camera.get_position().getY();
        double height_high = height*_camera.get_zoom() - _camera.get_position().getY();

        step_x *=_camera.get_zoom();
        step_y *=_camera.get_zoom();

        for(double x=width_low; x <= width_high; x+=step_x){
            _canvas.getGraphicsContext2D().strokeLine(x,height_low,x,height_high);
        }
        for (double y=height_low; y <= height_high; y+=step_y){
            _canvas.getGraphicsContext2D().strokeLine(width_low,y,width_high,y);
        }
    }





    @Override
    public void clearCtx(){
        _canvas.getGraphicsContext2D().clearRect(0, 0, _canvas.getWidth(), _canvas.getHeight());
    }
}
