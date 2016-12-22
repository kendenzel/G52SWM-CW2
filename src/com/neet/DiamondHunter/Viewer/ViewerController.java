package com.neet.DiamondHunter.Viewer;

import javafx.event.ActionEvent;
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

public class ViewerController extends TileMap implements Initializable {


    private boolean axePressed = false;
    private boolean shipPressed = false;

    private boolean axeDrawn = false;
    private boolean shipDrawn = false;

    @FXML
    Canvas viewer = new Canvas();
    @FXML
    Label mouse_pos = new Label();
    @FXML
    Label map_status = new Label();
    @FXML
    ToggleButton axe_tol = new ToggleButton();
    @FXML
    ToggleButton ship_tol = new ToggleButton();
    private Image axe = new Image(getClass().getResourceAsStream("ViewerResources/axe.jpg"));
    private Image ship = new Image(getClass().getResourceAsStream("ViewerResources/ship.jpg"));

    private final static String map_Blocked = "Cannot place item here,\nthis is a blocked tile!";
    private final static String map_Unblocked = "Item can be placed here!";

    private final static String RED = "#FF0000";
    private final static String GREEN = "#008000";

    private GraphicsContext g;
    private TileMap first = new TileMap();

    public void setXY() {
        viewer.setOnMouseMoved(event -> {
            String msg = String.valueOf((int)event.getSceneX()/16) + " , " + (int)event.getSceneY()/16;
            mouse_pos.setText(msg);
            if(!first.getMap((int)event.getSceneX()/16,(int)event.getSceneY()/16)){
                map_status.setTextFill(Color.web(RED));
                map_status.setText(map_Blocked);
            }else{
                map_status.setTextFill(Color.web(GREEN));
                map_status.setText(map_Unblocked);
            }
        });
    }

    @FXML
    protected void setItem(ActionEvent t){
        if(t.getSource() == axe_tol){
            axePressed =! axePressed;
            if(axePressed && shipPressed)
                shipPressed = false;

            System.out.println("Axe " + axePressed + "\n" + "Ship " + shipPressed + "\n");

        }
        if(t.getSource() == ship_tol){
            shipPressed =! shipPressed;
            if(shipPressed && axePressed)
                axePressed = false;
            System.out.println("Ship " + shipPressed + "\n" + "Axe " + axePressed + "\n");
        }
        {
            viewer.setOnMouseClicked(event ->  {
                int X = (int)event.getSceneX()/16;
                int Y = (int)event.getSceneY()/16;
                System.out.println(first.getMap(X,Y));

                if(first.getMap(X,Y)){
                    if(axePressed && !axeDrawn){
                        first.drawAxe(X,Y,g);
                        axeDrawn =! axeDrawn;
                    }
                    if(shipPressed && !shipDrawn) {
                        first.drawShip(X, Y, g);
                        shipDrawn =! shipDrawn;
                    }
                }
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = viewer.getGraphicsContext2D();
        try {
            first.tileMapManager(g);
            first.itemManager();
            axe_tol.setGraphic(new ImageView(axe));
            ship_tol.setGraphic(new ImageView(ship));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void clearItems() throws IOException {
        shipDrawn = false;
        axeDrawn = false;
        g.clearRect(0,0,640,640);
        refresh();
    }

    @FXML
    public void save(){
        first.save();
    }

    private void refresh() throws IOException {
        first.tileMapManager(g);
    }
}
