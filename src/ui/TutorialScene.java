package ui;

import application.KeyUtility;
import application.Main;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.element.SciFiButton;

public class TutorialScene extends VBox{
	private ImageView tutorialScene;
	private Text tutorialText,moveLeftText,moveRightText,shootingText,text;
	public TutorialScene() {
		loadResource();
		this.setAlignment(Pos.CENTER);
		this.setSpacing(30);
		StackPane stackpane=new StackPane();
		stackpane.getChildren().add(tutorialScene);
		SciFiButton mainMenu=new SciFiButton("Main Menu",200,50);
		stackpane.getChildren().addAll(moveRightText,moveLeftText,shootingText);
		moveRightText.setTranslateX(95);
		moveRightText.setTranslateY(-145);
		moveLeftText.setTranslateX(95);
		moveLeftText.setTranslateY(-10);
		shootingText.setTranslateX(95);
		shootingText.setTranslateY(125);
		mainMenu.setOnMouseClicked(e->{
			Main.sceneManeger.show(SceneType.MainScene);
		});
		
		this.setStyle("-fx-background-color: transparent;");
		this.getChildren().addAll(tutorialText,stackpane,text,mainMenu);
	}
	private void loadResource() {
		tutorialScene=new ImageView(new Image(ClassLoader.getSystemResource("picture/tutorialscene.png").toString()));
		tutorialText=new Text("Tutorial");
		text=new Text("If the spacecraft collides with buffobject,\n the spacecraft will be immuned for a while.");
		text.setStyle("-fx-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		tutorialText.setStyle("-fx-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+50+";");
		moveLeftText=new Text(KeyUtility.getMoveLeft().toString());
		moveLeftText.setStyle("-fx-fill: black;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		moveRightText=new Text(KeyUtility.getMoveRight().toString());
		moveRightText.setStyle("-fx-fill: black;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		shootingText=new Text(KeyUtility.getShooting().toString());
		shootingText.setStyle("-fx-fill: black;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		
	}
}
