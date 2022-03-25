package logic.entity;

import application.Main;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.RenderableHolder;

public class BuffObject extends CollidableEntity {
	
	protected Image image;
	public BuffObject() {
		// TODO Auto-generated constructor stub
		super();
		this.width=50;
		this.height=50;
		this.magnitudeVelocity=3;
		this.z=2;
		this.damage=0;
		this.position=new Point2D(Math.random()*Main.stage.getWidth()-this.getWidth(),0);
		this.vectorVelocity=new Point2D(0, 1);
	}
	
	public void buff(Player player) {
		player.setBuffed(true);
		destroy();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(this.image, getX(), getY(), this.getWidth(), this.getHeight());
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
