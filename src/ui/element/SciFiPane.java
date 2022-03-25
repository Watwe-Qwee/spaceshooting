package ui.element;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sharedObject.RenderableHolder;

public class SciFiPane extends BorderPane{
	public SciFiPane(int width,int height) {
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.setStyle("-fx-background-image:"+" url("+RenderableHolder.sciFiPaneURL+");"+
				 "-fx-background-repeat: no-repeat;" + 
				"-fx-background-position: center;"+
				"-fx-background-size: "+width+"px "+height+"px;"+
				"-fx-background-color: transparent;");
	}
}
