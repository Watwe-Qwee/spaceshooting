package ui;

import application.AudioUtility;
import application.KeyUtility;
import application.Main;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.gamecontroller.GameController;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import ui.element.SciFiButton;

public class GameScene extends StackPane{
	private GameController gameController;
	private Canvas gameField;
	private GraphicsContext gc;
	private AnimationTimer timer ;
	private DataBoard dataBoard; 
	private int i=0,j=0;
	public GameScene() {
		gameController=new GameController();
		gameField=new Canvas();
		this.getChildren().add(gameField);
		gc=gameField.getGraphicsContext2D();
		dataBoard=new DataBoard(gameController);
		this.getChildren().add(dataBoard);
		gameField.setWidth(Main.stage.getWidth());
		gameField.setHeight(Main.stage.getHeight());
		
		this.setPrefWidth(Main.stage.getWidth());
		this.setPrefHeight(Main.stage.getHeight());
		this.setStyle("-fx-background-color: transparent;");
		
		timer=new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				//gc.clearRect(0, 0, Main.stage.getWidth(), Main.stage.getHeight());
				dataBoard.update();
				checkPlayerDead();
				checkStopGame();
				paintBackGround();
				gameController.update();
				RenderableHolder.getInstance().update();
				paintComponent();
			}
		};
		timer.start();
		
	}
	private void paintBackGround() {
		if(900-j!=0) {
			WritableImage croppedImage = new WritableImage(RenderableHolder.backGround.get(i).getPixelReader(),
					0, j, 600, 900-j);
			gc.drawImage(croppedImage, 0, 0, 600, 900-j);	
		}
		if(j!=0) {
			WritableImage croppedImage2 = new WritableImage(RenderableHolder.backGround.get((i+1)%2).getPixelReader(),
					0, 0, 600, j);
			gc.drawImage(croppedImage2, 0,900-j, 600, j);
		}
		if(j==900) {
			i++;
			i%=2;
			j=0;
		}
		j++;
	}
	private void paintComponent() {
		for(IRenderable entity:RenderableHolder.getInstance().getEntities()) {
				entity.draw(gc);
		}
	}
	
	private void checkPlayerDead() {
		if(gameController.getPlayer().getHP()<=0) {
			this.getChildren().remove(dataBoard);
			this.getChildren().remove(gameField);
			
			AudioUtility.loseSFX.play();
			Text broke=new Text("Your spacecraft is Broken");
			Text broke2=new Text("Score : "+gameController.getScore());
			broke.setStyle(
					"-fx-fill: white;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+40+";");
			broke2.setStyle(
					"-fx-fill: white;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+40+";");
			VBox vbox=new VBox();
			vbox.setMaxWidth(Main.stage.getWidth());
			vbox.setMaxWidth(Main.stage.getWidth());
			vbox.setAlignment(Pos.CENTER);
			this.getChildren().add(vbox);
			SciFiButton button2=new SciFiButton("Restart", 150, 50);
			button2.setOnMouseClicked(e->{
				RenderableHolder.removeAll();
				Main.sceneManeger.show(SceneType.GameScene);
			});
			SciFiButton button3=new SciFiButton("Main Menu", 200, 50);
			button3.setOnMouseClicked(e->{
				Main.sceneManeger.show(SceneType.MainScene);
				RenderableHolder.removeAll();
			});
			vbox.setSpacing(20);
			vbox.getChildren().addAll(broke,broke2,button2,button3);
			this.timer.stop();
		}
	}
	
	private void checkStopGame() {
		if(KeyUtility.getKeyCollector().contains(KeyCode.ESCAPE)) {
			this.getChildren().remove(dataBoard);
			VBox vbox=new VBox();
			Text pause=new Text("Pause game");
			pause.setStyle(
					"-fx-fill: white;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+40+";");
			vbox.setMaxWidth(Main.stage.getWidth());
			vbox.setMaxWidth(Main.stage.getWidth());
			vbox.setAlignment(Pos.CENTER);
			this.getChildren().add(vbox);
			SciFiButton button1=new SciFiButton("Continue", 150, 50);
			button1.setOnMouseClicked(e->{
				this.timer.start();
				this.getChildren().remove(vbox);
				this.getChildren().add(dataBoard);
			});
			SciFiButton button2=new SciFiButton("Restart", 150, 50);
			button2.setOnMouseClicked(e->{
				RenderableHolder.removeAll();
				Main.sceneManeger.show(SceneType.GameScene);
			});
			SciFiButton button3=new SciFiButton("Main Menu", 200, 50);
			button3.setOnMouseClicked(e->{
				Main.sceneManeger.show(SceneType.MainScene);
				RenderableHolder.removeAll();
			});
			vbox.setSpacing(20);
			vbox.getChildren().addAll(pause,button1,button2,button3);
			this.timer.stop();
		}
	}

	public Canvas getGameField() {
		return gameField;
	}
	public void setGameField(Canvas gameField) {
		this.gameField = gameField;
	}
	public AnimationTimer getTimer() {
		return timer;
	}
	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}
	
}
