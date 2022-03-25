package logic.entity;

import java.util.ArrayList;
import java.util.List;

import application.KeyUtility;
import application.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class Player extends HPEntity implements Damageable,Shootable{
	private int shootingDelay;
	private double bulletDamage;
	private boolean buffed;
	public Player() {
		super();
		this.buffed=false;
		this.width=50;
		this.height=50;
		this.magnitudeVelocity=5;
		this.vectorVelocity=new Point2D(0,0);
		this.position=new Point2D(275,675);
		this.shootingDelay=0;
		this.getCenter();
		this.HP=100;
		this.z=1;
		this.damage=20;
		this.bulletDamage=30;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.spaceCraft, getX(), getY(),this.width,this.height);
	}

	@Override
	public Bullet shoot() {
		// TODO Auto-generated method stub
		shootingDelay=SHOOTING_DELAY;
		return new PlayerBullet(this);
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
	public void countdown() {
		// TODO Auto-generated method stub
		if(shootingDelay>0)
			shootingDelay--;
	}
	public boolean dumpingEdgeLeft() {
		if(this.getX()>0)
			return false;
		return true;
	}
	public boolean dumpingEdgeRight() {
		if(this.getX()<Main.stage.getWidth()-this.width)
			return false;
		return true;
	}
	public int getShootingDelay() {
		return shootingDelay;
	}
	public void setShootingDelay(int shootingDelay) {
		this.shootingDelay = shootingDelay;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		move();
		countdown();
	}
	@Override
	public double getBulletDamage() {
		// TODO Auto-generated method stub
		return bulletDamage;
	}
	public void setBulletDamage(double bulletDamage) {
		this.bulletDamage = bulletDamage;
	}
	public boolean isBuffed() {
		return buffed;
	}
	public void setBuffed(boolean buffed) {
		this.buffed = buffed;
	}
}
