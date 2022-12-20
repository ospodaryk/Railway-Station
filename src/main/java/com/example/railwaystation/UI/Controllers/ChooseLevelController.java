package com.example.railwaystation.UI.Controllers;

import com.example.railwaystation.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseLevelController implements Initializable {

    public static Rectangle rectangle;

    static {
        rectangle = new Rectangle(0, 0);
    }

    @FXML
    public Slider lightnessSlider;
    public Canvas colorCanvas;
    public Canvas rectangleCanvas;
    public Text hslLine;
    public Text rgbLine;
    public ImageView imageOriginal;
    public ImageView imageView;
    public Text lightnessText;
    public Image image;
    public ComboBox colorsComboBox;
    public static String colorFromComboBox;


    @FXML
    private void chooseLevel1() throws IOException {
        App.setRoot("level1");
    }

    @FXML
    private void chooseLevel2() throws IOException {
        App.setRoot("level2");
    }

    @FXML
    private void chooseLevel3()throws IOException {
        App.setRoot("level3");
    }

    @FXML
    private void chooseLevel4() throws IOException{
        App.setRoot("level4");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}