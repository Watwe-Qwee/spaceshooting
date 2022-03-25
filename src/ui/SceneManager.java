package ui;

import application.KeyUtility;
import application.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class SceneManager extends StackPane{
	private SubScene selectedScene;
	public SceneManager() {
		super();
		this.getChildren().add(RenderableHolder.galaxyView);
		selectedScene=new SubScene(new MainScene(), 600, 900);
	}
	public void show(SceneType sceneType) {
		this.getChildren().remove(selectedScene);
		KeyUtility.setCurrentKeyCode(null);
		switch(sceneType) {
		case GameScene:
			selectedScene=new SubScene(new GameScene(), 600, 900);
			this.getChildren().add(selectedScene);
			play("slider");
			break;
		case MainScene:
			
			selectedScene=new SubScene(new MainScene(), 600, 900);
			this.getChildren().add(selectedScene);
			play("slider");
			break;
		case SettingScene:
			selectedScene=new SubScene(new SettingScene(), 600, 900);
			selectedScene.setTranslateY(0);
			this.getChildren().add(selectedScene);
			play("slider");
			break;
		case TutorialScene:
			selectedScene=new SubScene(new TutorialScene(), 600, 900);
			selectedScene.setTranslateY(0);
			this.getChildren().add(selectedScene);
			play("slider");
			break;
		}
			
	}
	private void play(String string) {
		// TODO Auto-generated method stub
		switch(string) {
			case "slider":
				selectedScene.setTranslateX(Main.stage.getWidth());
				AnimationTimer timer =new AnimationTimer() {
					@Override
					public void handle(long arg0) {
						// TODO Auto-generated method stub
						if(selectedScene.getTranslateX()>0) {
							selectedScene.setTranslateX(selectedScene.getTranslateX()-50);
						}
						else {
							this.stop();
						}
					}
				};
				timer.start();
				break;
		}
	}
}
