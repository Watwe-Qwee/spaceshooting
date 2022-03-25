package ui;

import application.AudioUtility;
import application.Main;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;
import ui.element.SciFiButton;
import ui.element.SciFiPane;

public class MainScene extends VBox{
	public MainScene() {
		AudioUtility.backgroundSFX.play();
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: transparent;");
		this.setSpacing(30);
		this.setPrefWidth(Main.stage.getWidth());
		this.setPrefHeight(Main.stage.getHeight());
		
		SciFiPane sciFiPane=new SciFiPane(200,300);
		VBox inSciFiPane=new VBox(20);
		inSciFiPane.setAlignment(Pos.CENTER);
		
		SciFiButton start=new SciFiButton("Start", 100, 40);
		start.setOnMouseClicked(e->{
			Main.sceneManeger.show(SceneType.GameScene);
		});
		SciFiButton tutorial=new SciFiButton("Tutorial", 100, 40);
		tutorial.setOnMouseClicked(e->{
			Main.sceneManeger.show(SceneType.TutorialScene);
		});
		SciFiButton setting=new SciFiButton("Setting", 100, 40);
		setting.setOnMouseClicked(e->{
			Main.sceneManeger.show(SceneType.SettingScene);
		});
		SciFiButton quit=new SciFiButton("Quit", 100, 40);
		quit.setOnMouseClicked(e->{
			Main.stage.close();
		});
		
		inSciFiPane.getChildren().addAll(start,tutorial,setting,quit);
		sciFiPane.setCenter(inSciFiPane);
		Text text=new Text("Credit : 6231374221 Anon Ongsakul");
		text.setStyle("-fx-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+10+";");
		this.getChildren().addAll(RenderableHolder.logoView,sciFiPane,text);
	}
}
