package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class RenderableHolder {
	private static final RenderableHolder INSTANCE = new RenderableHolder();
	
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static ArrayList<Image> fireEffect,backGround,villains;
	public static Image galaxy,logo,asteroid,spaceCraft,playerBullet,buffedEffect;
	public static Image villainBullet;
	public static Image bomdEffectSFX1, bomdEffectSFX2, bomdEffectSFX3;
	public static Image heartBuffObject,bulletBuffObject;
	public static ImageView logoView,galaxyView,heartView;
	public static Background galaxyBackGround;
	public static String sciFiButtonUnPressedURL,sciFiButtonPressedURL,sciFiPaneURL;
	
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return INSTANCE;
	}

	public static void loadResource() {
		
		galaxy=new Image(ClassLoader.getSystemResource("picture/background.png").toString());
		galaxyView=new ImageView(galaxy);
		BackgroundImage galaxyBackGroundImage = new BackgroundImage(galaxy,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundRepeat.NO_REPEAT,  
                BackgroundPosition.DEFAULT,  
                   BackgroundSize.DEFAULT);
		galaxyBackGround= new Background(galaxyBackGroundImage);
		
		logo=new Image(ClassLoader.getSystemResource("picture/logo.png").toString());
		logoView =new ImageView(logo);
		
		sciFiButtonPressedURL="'"+ClassLoader.getSystemResource("picture/scifibuttonpressed.png").toString()+"'";
		sciFiButtonUnPressedURL="'"+ClassLoader.getSystemResource("picture/scifibuttonunpressed.png").toString()+"'";
		sciFiPaneURL="'"+ClassLoader.getSystemResource("picture/scififrame.png").toString()+"'";
		
		asteroid=new Image(ClassLoader.getSystemResource("picture/asteroid.png").toString());
		spaceCraft=new Image(ClassLoader.getSystemResource("picture/spacecraft.png").toString());
		playerBullet=new Image(ClassLoader.getSystemResource("picture/playerbullet.png").toString());
		
		villains=new ArrayList<Image>();
		villains.add(new Image(ClassLoader.getSystemResource("picture/villain1.png").toString()));
		villains.add(new Image(ClassLoader.getSystemResource("picture/villain2.png").toString()));
		villains.add(new Image(ClassLoader.getSystemResource("picture/villain3.png").toString()));
		villains.add(new Image(ClassLoader.getSystemResource("picture/villain4.png").toString()));
		
		fireEffect=new ArrayList<>();
		for(int i=1;i<=8;i++) {
			fireEffect.add(new Image(ClassLoader.getSystemResource("picture/fireeffect/fireeffect"+i+".png").toString()));
		}
		buffedEffect = new Image(ClassLoader.getSystemResource("picture/buffedeffect.png").toString());
		
		backGround=new ArrayList<Image>();
		backGround.add(galaxy);
		backGround.add(new Image(ClassLoader.getSystemResource("picture/backgroundflipped.png").toString()));
		
		villainBullet=new Image(ClassLoader.getSystemResource("picture/villainbullet.png").toString()); 
		
		heartBuffObject=new Image(ClassLoader.getSystemResource("picture/heartbuffobject.png").toString());
		bulletBuffObject=new Image(ClassLoader.getSystemResource("picture/bulletbuffobject.png").toString());
		
		heartView=new ImageView(new Image(ClassLoader.getSystemResource("picture/heart.png").toString()));
		heartView.setFitWidth(50);heartView.setFitHeight(50);
	}

	public void add(IRenderable entity) {
		//System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	public static void removeAll() {
		for(int i=INSTANCE.getEntities().size()-1;i>=0;i--) {
			INSTANCE.getEntities().remove(i);
		}
	}
}
