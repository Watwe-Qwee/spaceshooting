package logic.entity;

import javafx.geometry.Point2D;
import sharedObject.RenderableHolder;

public class VillainBullet extends Bullet{

	public VillainBullet(Villain owner) {
		super(owner);
		this.magnitudeVelocity=9;
		this.vectorVelocity=new Point2D(0,1);
		this.image=RenderableHolder.villainBullet;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void damage(CollidableEntity other) {
		// TODO Auto-generated method stub
		destroy();
		if(other instanceof Player) {
			if(!((Player) other).isBuffed())
				((HPEntity) other).setHP(((HPEntity) other).getHP()-this.damage);
		}
		else {
			super.damage(other);
		}
	}
	
}
