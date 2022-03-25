package ui.element;

import java.util.ArrayList;

import application.KeyUtility;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class SettingBox extends HBox{
	private static final ArrayList<SettingBox> SETTINGBOXES=new ArrayList<SettingBox>();
	private Label label;
	private AnimationTimer timer;
	private SciFiButton button;
	static{
		SETTINGBOXES.add(new SettingBox("Move Left"));
		SETTINGBOXES.add(new SettingBox("Move Right"));
		SETTINGBOXES.add(new SettingBox("Shooting"));
	}
	
	public SettingBox(String string) {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.label=new Label(string);
		this.label.setStyle(("-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";"));
		setUp(string);
		button.setOnMouseClicked(e->{
			button.setClicked(!button.isClicked());
			if(button.isClicked())
			{
				pressed();
			}
			else 
			{
				unPressed();
			}
		});
		this.getChildren().addAll(button,label);
	}
	private void pressed() {
		timer.start();
		this.label.setStyle("-fx-text-fill: red;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";");
		for(SettingBox box:SETTINGBOXES) {
			if(!box.equals(this)) {
				box.unPressed();
			}
		}
	}
	private void unPressed() {
		timer.stop();
		this.label.setStyle(("-fx-text-fill: white;"+
				"-fx-font-family: Nasalization;"+
				"-fx-font-size:"+20+";"));
		this.getButton().setClicked(false);
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public AnimationTimer getTimer() {
		return timer;
	}
	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}
	public SciFiButton getButton() {
		return button;
	}
	public void setButton(SciFiButton button) {
		this.button = button;
	}
	private void setUp(String string) {
		switch (string) {
		case "Move Left":
			timer =new AnimationTimer() {			
				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if(!(KeyUtility.getCurrentKeyCode()==null)){
						try {
							checkingException(KeyUtility.getCurrentKeyCode());
							KeyUtility.setMoveLeft(KeyUtility.getCurrentKeyCode());
							KeyUtility.setCurrentKeyCode(null);;						
						}
						catch (KeySettingException e) {
							// TODO: handle exception
							unPressed();
						}
					}
					button.setText(KeyUtility.getMoveLeft().toString());
				}
			};
			button=new SciFiButton(KeyUtility.getMoveLeft().toString(), 100, 40);
			break;
		case "Move Right":
			timer =new AnimationTimer() {			
				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if(!(KeyUtility.getCurrentKeyCode()==null)){
						try {
							checkingException(KeyUtility.getCurrentKeyCode());
							KeyUtility.setMoveRight(KeyUtility.getCurrentKeyCode());
							KeyUtility.setCurrentKeyCode(null);						
						}
						catch (KeySettingException e) {
							// TODO: handle exception
							unPressed();
						}
					}
					button.setText(KeyUtility.getMoveRight().toString());
				}
			};
			button=new SciFiButton(KeyUtility.getMoveRight().toString(), 100, 40);
			break;
		case "Shooting":
			timer =new AnimationTimer() {			
				@Override
				public void handle(long arg0) {
					// TODO Auto-generated method stub
					if(!(KeyUtility.getCurrentKeyCode()==null)){
						try {
							checkingException(KeyUtility.getCurrentKeyCode());
							KeyUtility.setShooting(KeyUtility.getCurrentKeyCode());
							KeyUtility.setCurrentKeyCode(null);;
						}
						catch (KeySettingException e) {
							// TODO: handle exception
							unPressed();
						}
					}
					button.setText(KeyUtility.getShooting().toString());
				}
			};
			button=new SciFiButton(KeyUtility.getShooting().toString(), 100, 40);
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
	}
	public static ArrayList<SettingBox> getSettingboxes() {
		return SETTINGBOXES;
	}
	private void checkingException(KeyCode keycode) throws KeySettingException{
		if(keycode.equals(KeyCode.ESCAPE)) {
			throw new KeySettingException(keycode);
		}
		else if(keycode.equals(KeyCode.CONTROL)) {
			throw new KeySettingException(keycode);
		}
		else if(keycode.equals(KeyCode.CAPS)) {
			throw new KeySettingException(keycode);
		}
		else if(keycode.equals(KeyCode.SHIFT)) {
			throw new KeySettingException(keycode);
		}
	}
}
