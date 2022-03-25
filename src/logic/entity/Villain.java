package logic.entity;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class Villain extends HPEntity implements Shootable,Damageable{
	private Image image;
	private double shootingDelay;
	private double bulletDamage;
	private Point2D target;
	public Villain() {
		super();
		Random rand=new Random();
		this.width=50;
		this.height=50;
		this.magnitudeVelocity=0;
		this.vectorVelocity=new Point2D(0,1);
		randomNewTarget();
		
		this.shootingDelay=rand.nextInt(SHOOTING_DELAY2);
		this.getCenter();
		this.HP=100;
		this.z=1;
		this.damage=20;
		this.bulletDamage=25;
		this.image=RenderableHolder.villains.get(rand.nextInt(4));
	}
	public void randomNewTarget() {
		this.target=new Point2D(this.width+Math.random()*500, 100+Math.random()*300);
		this.position=new Point2D(this.target.getX(),0);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(this.position.getY()<this.target.getY())
			magnitudeVelocity=5*(1-Math.exp(-(this.target.getY()-this.position.getY())/(this.target.getY())));
		else 
			magnitudeVelocity=0;
		super.move();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(this.image, getX(), getY(),this.width,this.height);
	}
	@Override
	public void damage(CollidableEntity other) {
		// TODO Auto-generated method stub
		if(other instanceof HPEntity ) {
			this.HP-=other.getDamage();
			((HPEntity) other).setHP(((HPEntity) other).getHP()-this.damage);
		}
		else if(other instanceof CollidableEntity) {
			this.HP-=((CollidableEntity) other).getDamage();
			other.setVisible(false);
			other.setDestroyed(true);
		}
	}
	@Override
	public Bullet shoot() {
		// TODO Auto-generated method stub
		if(shootingDelay<=0) {
			shootingDelay=SHOOTING_DELAY2*(1+Math.random());
			return new VillainBullet(this);
		}
		return null;
	}
	@Override
	public void countdown() {
		// TODO Auto-generated method stub
		if(shootingDelay>0)
			shootingDelay--;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		move();
		countdown();
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public double getShootingDelay() {
		return shootingDelay;
	}
	public void setShootingDelay(double shootingDelay) {
		this.shootingDelay = shootingDelay;
	}
	public Point2D getTarget() {
		return target;
	}
	public void setTarget(Point2D target) {
		this.target = target;
	}
	public void setBulletDamage(double bulletDamage) {
		this.bulletDamage = bulletDamage;
	}
	@Override
	public double getBulletDamage() {
		// TODO Auto-generated method stub
		return bulletDamage;
	}
}
