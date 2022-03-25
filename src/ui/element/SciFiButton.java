package ui.element;

import application.AudioUtility;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import sharedObject.RenderableHolder;

public class SciFiButton extends Button{
	private boolean clicked;
	public SciFiButton(String title,int width,int height) {
		super(title);
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.buttonExit();
		this.clicked=false;
		this.setOnMouseEntered(e->{
			this.buttonEnter();
			AudioUtility.scifiClickSFX.play();
			e.consume();
		});
		this.setOnMouseExited(e->{
			this.buttonExit();
			e.consume();
		});
	}
	private void buttonExit() {
		this.setStyle("-fx-background-image:"+" url("+RenderableHolder.sciFiButtonUnPressedURL+");"
				+ "-fx-background-repeat: no-repeat;" + 
				"-fx-background-position: center;"+
				"-fx-background-size: "+getPrefWidth()+"px "+getPrefHeight()+"px;"+
				"-fx-background-color: transparent;"+
				"-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+getPrefHeight()/2.5+";");
	}
	private void buttonEnter() {
		this.setStyle("-fx-background-image:"+" url("+RenderableHolder.sciFiButtonPressedURL+");"
				+ "-fx-background-repeat: no-repeat;" + 
				"-fx-background-position: center;"+
				"-fx-background-size: "+getPrefWidth()+"px "+getPrefHeight()+"px;"+
				"-fx-background-color: transparent;"+
				"-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+getPrefHeight()/2.5+";");
	}
	public boolean isClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
}
