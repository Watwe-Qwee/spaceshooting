package logic.entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class CollidableEntity extends Entity {
	
	protected double damage;
	protected CollidableEntity() {
		super();
	}
	
	public boolean collideWith(CollidableEntity other) {
		boolean x=((this.getX()<other.getX()+other.getWidth())&&(this.getX()+this.getWidth()>other.getX())),
				y=((this.getY()<other.getY()+other.getHeight())&&(this.getY()+this.getHeight()>other.getY()));
		
		return x&&y;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	
}
