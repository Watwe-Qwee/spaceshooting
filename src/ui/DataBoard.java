package ui;

import application.Updatable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.entity.Player;
import logic.gamecontroller.GameController;
import sharedObject.RenderableHolder;

public class DataBoard  extends Pane implements Updatable{
	private Text HP;
	private Text Score;
	private Player player;
	private GameController gameController;
	public DataBoard(GameController gameController) {
		this.player=gameController.getPlayer();
		this.gameController=gameController;
		this.setTranslateY(75);
		this.setMaxHeight(900);
		this.setMaxWidth(600);
		HBox hbox=new HBox(),hbox2=new HBox();
		hbox.setPrefWidth(600);
		hbox.setPrefHeight(100);
		hbox2.setPrefWidth(600);
		hbox2.setPrefHeight(100);
		hbox2.setMaxHeight(100);
		hbox2.setAlignment(Pos.CENTER_LEFT);
		hbox.setAlignment(Pos.CENTER);
		HP=new Text("HP : "+1);
		Score=new Text("Score : "+1);
		Score.setStyle(
				"-fx-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+40+";");
		HP.setStyle(
				"-fx-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		hbox.setTranslateY(0);
		hbox2.setTranslateY(650);
		hbox2.setTranslateX(20);
		hbox.getChildren().add(Score);
		hbox2.setSpacing(20);
		hbox2.getChildren().addAll(RenderableHolder.heartView,HP);
		this.getChildren().addAll(hbox,hbox2);
	}
	public void update() {
		HP.setText("HP : "+player.getHP());
		Score.setText("Score : "+gameController.getScore());
	}
}
