package ui.element;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class KeySettingException extends Exception{
	public KeySettingException(KeyCode keycode) {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog.");
		alert.setHeaderText(null);
		alert.setContentText(keycode.toString()+" is not allowed for setting.");
		alert.show();
	}
}
