package com.neet.DiamondHunter.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewerController implements Initializable {

    @FXML
    Canvas viewer = new Canvas();
    @FXML
    ToggleButton axe_tol = new ToggleButton();
    @FXML
    ToggleButton ship_tol = new ToggleButton();
    private Image axe = new Image(getClass().getResourceAsStream("ViewerResources/axe.jpg"));
    private Image ship = new Image(getClass().getResourceAsStream("ViewerResources/ship.jpg"));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        axe_tol.setGraphic(new ImageView(axe));
        ship_tol.setGraphic(new ImageView(ship));
    }
}
