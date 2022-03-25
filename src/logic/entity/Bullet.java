package logic.entity;

import application.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class Bullet extends CollidableEntity implements Damageable{
	protected Image image;
	
	public Bullet(Shootable owner) {
		this.width=5;
		this.height=10;
		this.center=((Entity) owner).getCenter();
		this.position=this.calPosition();
		this.damage=owner.getBulletDamage();
		this.z=0;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(this.image, getX(), getY(),this.width,this.height);
	}

	@Override
	public void damage(CollidableEntity other) {
		// TODO Auto-generated method stub
		if(other instanceof HPEntity) {
			destroy();
			((HPEntity) other).setHP(((HPEntity) other).getHP()-this.damage);
		}
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		move();
		edgeDumping();
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}


}
