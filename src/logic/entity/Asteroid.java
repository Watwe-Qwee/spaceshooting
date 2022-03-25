package logic.entity;

import application.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Asteroid extends HPEntity implements Damageable{
	
	public Asteroid(Player player) {
		super();
		this.HP=100;
		this.width=40;
		this.height=40;
		this.magnitudeVelocity=2;
		this.z=2;
		this.damage=40;
		this.position=new Point2D(Math.random()*Main.stage.getWidth(),0);
		Point2D delta=position.subtract(player.getCenter());
		double theta=Math.atan(delta.getY()/delta.getX());
		if(delta.getX()>0)
			this.vectorVelocity=new Point2D(-Math.cos(theta), Math.abs(Math.sin(theta)));
		else
			this.vectorVelocity=new Point2D(Math.cos(theta), Math.abs(Math.sin(theta)));
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.asteroid, getX(), getY(),this.width,this.height);
	}

	@Override
	public void damage(CollidableEntity other) {
		// TODO Auto-generated method stub
		if(other instanceof HPEntity) {
			if(other instanceof Player) {
				if(!((Player) other).isBuffed()) {
					((HPEntity) other).setHP(((HPEntity) other).getHP()-this.damage);
				}
				this.HP=0;
				destroy();
			}
			else {
				((HPEntity) other).setHP(((HPEntity) other).getHP()-this.damage);
			}
			this.HP-=other.getDamage();
		}
		else if(other instanceof CollidableEntity) {
			this.HP-=((CollidableEntity) other).getDamage();
			other.setVisible(false);
			other.setDestroyed(true);
		}
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		move();
		edgeDumping();
	}

}
