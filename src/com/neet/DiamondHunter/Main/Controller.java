package com.neet.DiamondHunter.Main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    private Stage primaryStage = new Stage();

    @FXML
    private RadioButton game = new RadioButton();

    @FXML
    private RadioButton gameviewer = new RadioButton();

    @FXML
    public void start() throws Exception{
        if(game.isSelected()){
            gamelaunch();
        }

        if(gameviewer.isSelected()){
        	viewerlaunch();
		}
    }


    public void gamelaunch(){
        JFrame window = new JFrame("Diamond Hunter");

        window.add(new GamePanel());

        window.setResizable(false);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void viewerlaunch() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Viewer/ViewerApp.fxml"));
        primaryStage.setTitle("Map Viewer");
        Scene first = new Scene(root,840,640);
        primaryStage.setScene(first);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
