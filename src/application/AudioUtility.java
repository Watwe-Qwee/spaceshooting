package application;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	private static ArrayList<AudioClip> SFXes=new ArrayList<>(),musics=new ArrayList<>();
	public static AudioClip backgroundSFX,shootingSFX,scifiClickSFX,collisionSFX,loseSFX;
	public static AudioClip shootingStarSFX,villainShootingSFX,explosionSFX;
	static {
		loadResource();
	}
	public static void loadResource() {
		// TODO Auto-generated method stub
		scifiClickSFX =new AudioClip(ClassLoader.getSystemResource("sfx/scificlickSFX.wav").toString());
		SFXes.add(scifiClickSFX);
		backgroundSFX =new AudioClip(ClassLoader.getSystemResource("sfx/backgroundSFX.mp3").toString());
		backgroundSFX.setVolume(0.5);
		backgroundSFX.setCycleCount(AudioClip.INDEFINITE);
		musics.add(backgroundSFX);
		shootingSFX =new AudioClip(ClassLoader.getSystemResource("sfx/shooting.wav").toString());
		SFXes.add(shootingSFX);
		collisionSFX=new AudioClip(ClassLoader.getSystemResource("sfx/collisionSFX.aiff").toString());
		SFXes.add(collisionSFX);
		shootingStarSFX=new AudioClip(ClassLoader.getSystemResource("sfx/shooting_starSFX.wav").toString());
		SFXes.add(shootingSFX);
		loseSFX=new AudioClip(ClassLoader.getSystemResource("sfx/loseSFX.wav").toString());
		SFXes.add(loseSFX);
		villainShootingSFX=new AudioClip(ClassLoader.getSystemResource("sfx/villainshootingSFX.wav").toString());
		SFXes.add(villainShootingSFX);
		explosionSFX=new AudioClip(ClassLoader.getSystemResource("sfx/explosionSFX.wav").toString());
		SFXes.add(explosionSFX);
		
		setVolumeAllMusics(0.25);
		setVolumeAllSFXes(0.25);
	}
	
	public static void setVolumeAllSFXes(double value){
		for(AudioClip audioclip:SFXes) {
			audioclip.setVolume(value);
		}
	}
	public static void setVolumeAllMusics(double value) {
		for(AudioClip audioclip:musics) {
			audioclip.setVolume(value);
			audioclip.stop();
			audioclip.play();
			audioclip.setCycleCount(AudioClip.INDEFINITE);
		}
	}
}
