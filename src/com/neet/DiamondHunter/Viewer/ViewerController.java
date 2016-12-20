package com.neet.DiamondHunter.Viewer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
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



    private GraphicsContext g;
    private TileMap first = new TileMap();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = viewer.getGraphicsContext2D();
        try {
            first.tileMapManager(g);
            axe_tol.setGraphic(new ImageView(axe));
            ship_tol.setGraphic(new ImageView(ship));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
