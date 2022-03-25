package application;

import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class KeyUtility {
	private static HashSet<KeyCode> keyCollector=new HashSet<KeyCode>();
	private static KeyCode currentKeyCode=null;
	private static KeyCode moveLeft=KeyCode.LEFT;
	private static KeyCode moveRight=KeyCode.RIGHT;
	private static KeyCode shooting=KeyCode.SPACE;
	
	public static void add(KeyCode keycode) {
		keyCollector.add(keycode);
		currentKeyCode=keycode;
	}
	
	public static void remove(KeyCode keycode) {
		keyCollector.remove(keycode);
	}

	public static HashSet<KeyCode> getKeyCollector() {
		return keyCollector;
	}

	public static void setKeyCollector(HashSet<KeyCode> keyCollector) {
		KeyUtility.keyCollector = keyCollector;
	}

	public static KeyCode getCurrentKeyCode() {
		return currentKeyCode;
	}

	public static void setCurrentKeyCode(KeyCode currentKeyCode) {
		KeyUtility.currentKeyCode = currentKeyCode;
	}

	public static KeyCode getMoveLeft() {
		return moveLeft;
	}

	public static void setMoveLeft(KeyCode moveLeft) {
		KeyUtility.moveLeft = moveLeft;
	}

	public static KeyCode getMoveRight() {
		return moveRight;
	}

	public static void setMoveRight(KeyCode moveRight) {
		KeyUtility.moveRight = moveRight;
	}

	public static KeyCode getShooting() {
		return shooting;
	}

	public static void setShooting(KeyCode shooting) {
		KeyUtility.shooting = shooting;
	}
	
}
