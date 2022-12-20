package com.example.railwaystation.UI.Controllers;

import com.example.railwaystation.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {

    /*@FXML
    private void switchToFractal() throws IOException {
        App.setRoot("fractal");
    }*/

    @FXML
    private void switchToLevel() throws IOException {
        App.setRoot("chooselevel");
    }

}
