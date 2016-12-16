// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package com.neet.DiamondHunter.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("Launcher.fxml"));
			primaryStage.setTitle("Launcher");
			Scene scene = new Scene(root,300,150);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}