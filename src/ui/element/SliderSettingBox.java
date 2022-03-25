package ui.element;

import application.AudioUtility;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SliderSettingBox extends VBox{
	private double value=1;
	private String type;
	private HBox hbox1=new HBox(),hbox2=new HBox();
	private Label label;
	private Slider slider;
	public SliderSettingBox(String string) {
		switch (string) {
		case "Music":
			slider=new Slider(0,1,AudioUtility.backgroundSFX.getVolume());
			break;
		case "SFX":
			slider=new Slider(0,1,AudioUtility.collisionSFX.getVolume());
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
		this.setMaxWidth(300);
		label =new Label(string);
		this.type=string;
		this.setAlignment(Pos.CENTER);
		hbox1.getChildren().add(label);
		hbox2.getChildren().add(slider);
		hbox1.setAlignment(Pos.CENTER_LEFT);
		hbox2.setAlignment(Pos.CENTER);
		label.setStyle("-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		slider.setOnMousePressed(e->{
			label.setStyle("-fx-text-fill: red;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+20+";");
		});
		slider.setOnMouseReleased(e->{
			setUpValue(slider.getValue());
			value=slider.getValue();
			label.setStyle("-fx-text-fill: white;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+20+";");
		});
		CheckBox checkbox=new CheckBox("Mute");
		checkbox.setStyle("-fx-text-fill: white;"+
					"-fx-font-family: Nasalization;"+
					"-fx-font-size:"+10+";");
		checkbox.setOnAction(e->{
			if(checkbox.isSelected()) {
				value=slider.getValue();
				slider.setValue(0);
				setUpValue(0);
			}
			else {
				slider.setValue(value);
				setUpValue(value);
			}
		});
		hbox2.getChildren().add(checkbox);
		hbox2.setSpacing(20);
		this.getChildren().addAll(hbox1,hbox2);
	}
	private void setUpValue(double value) {
		switch (type) {
		case "Music":
			AudioUtility.setVolumeAllMusics(value);
			break;
		case "SFX":
			AudioUtility.setVolumeAllSFXes(value);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}
}
