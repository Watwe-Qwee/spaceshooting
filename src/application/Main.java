package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.SceneManager;
import ui.SceneType;
public class Main extends Application{
	public static Stage stage;
	public static SceneManager sceneManeger;
	
	static {
		loadResource();
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Main.stage=stage;
		Main.sceneManeger=new SceneManager();
		stage.setWidth(600);
		stage.setHeight(900);
		stage.setTitle("SpaceCraft");
		
		Scene scene =new Scene(sceneManeger);	
		Main.sceneManeger.show(SceneType.MainScene);
		
		scene.setOnKeyPressed(e->{
			KeyUtility.add(e.getCode());
		});
		scene.setOnKeyReleased(e->{
			KeyUtility.remove(e.getCode());
		});
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}
	public static void main(String[] args) {
        launch(args);
    }
	public static void loadResource() {
		String etnocentricFontURL= ClassLoader.getSystemResource("font/ethnocentric.ttf").toString();
		Font.loadFont(etnocentricFontURL, 50);
		String nasalisationFontURL=ClassLoader.getSystemResource("font/nasalization-rg.ttf").toString();
		Font.loadFont(nasalisationFontURL, 50);
	}
}
