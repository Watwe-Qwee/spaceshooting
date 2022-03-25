package ui;

import java.util.ArrayList;

import application.AudioUtility;
import application.KeyUtility;
import application.Main;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.element.SciFiButton;
import ui.element.SciFiPane;
import ui.element.SettingBox;
import ui.element.SliderSettingBox;

public class SettingScene extends VBox{
	public SettingScene() {
		AudioUtility.backgroundSFX.play();
		this.setStyle("-fx-background-color: transparent;");
		this.setPrefWidth(Main.stage.getWidth());
		this.setPrefHeight(Main.stage.getHeight());
		this.setAlignment(Pos.CENTER);
		this.setUp();
	}
	private void setUp() {
		Label setting=new Label("Setting");
		setting.setStyle("-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+40+";");
		SciFiPane settingPane =new SciFiPane(200, 50);
		settingPane.setCenter(setting);
		
		VBox vbox=new VBox();
		HBox hbox1=new HBox(),hbox2=new HBox(),hbox3=new HBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPrefHeight(200);
		vbox.setPrefWidth(400);
		
		
		SciFiButton main=new SciFiButton("Menu",100,40);
		main.setOnMouseClicked(e->{
			Main.sceneManeger.show(SceneType.MainScene);
			for(SettingBox box:SettingBox.getSettingboxes()) {
				box.getTimer().stop();
				box.getButton().setClicked(false);
				box.getLabel().setStyle("-fx-text-fill: white;"+
							"-fx-font-family: Nasalization;"+
							"-fx-font-size:"+20+";");
			}
		});
		hbox3.getChildren().add(main);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(10);
		vbox.getChildren().addAll(new SliderSettingBox("Music"),new SliderSettingBox("SFX"));
		vbox.getChildren().addAll(SettingBox.getSettingboxes().get(0),SettingBox.getSettingboxes().get(1),SettingBox.getSettingboxes().get(2),main);
		vbox.setSpacing(20);
		this.getChildren().addAll(settingPane,vbox);
		
		this.setSpacing(40);
	}
	
}
