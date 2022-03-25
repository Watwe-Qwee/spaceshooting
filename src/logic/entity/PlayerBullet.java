package logic.entity;

import javafx.geometry.Point2D;
import sharedObject.RenderableHolder;

public class PlayerBullet extends Bullet{

	public PlayerBullet(Player owner) {
		super(owner);
		this.magnitudeVelocity=10;
		this.vectorVelocity=new Point2D(0,-1);
		this.image=RenderableHolder.playerBullet;
		// TODO Auto-generated constructor stub
	}

}
