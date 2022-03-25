package logic.entity;

import application.Main;
import application.Updatable;
import javafx.geometry.Point2D;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable,Movable,Updatable{
	protected double width,height,magnitudeVelocity;
	protected Point2D position,center,vectorVelocity;
	protected int z;
	protected boolean visible,destroyed;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	public void destroy() {
		this.visible=false;
		this.destroyed=true;
	}
	public void edgeDumping() {
		if(getX()<0 || getX()>Main.stage.getWidth()) {
			destroy();
		}
		if(getY()<0 || getY()>Main.stage.getHeight()) {
			destroy();
		}
	}
	public void move() {
		// TODO Auto-generated method stub
		this.position=this.position.add(vectorVelocity.multiply(magnitudeVelocity));
	}
	
	
	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Point2D getPosition() {
		return position;
	}
	
	public Point2D calPosition() {
		return new Point2D(center.getX()-width/2,center.getY()-height/2);
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Point2D getCenter() {
		center=new Point2D(position.getX()+width/2,position.getY()+height/2);
		return center;
	}
	

	public void setCenter(Point2D center) {
		this.center = center;
	}
	public double getX(){
		return position.getX();
	}
	public double getY(){
		return position.getY();
	}
	public double getMagnitudeVelocity() {
		return magnitudeVelocity;
	}
	public void setMagnitudeVelocity(double magnitudeVelocity) {
		this.magnitudeVelocity = magnitudeVelocity;
	}
	public Point2D getVectorVelocity() {
		return vectorVelocity;
	}
	public void setVectorVelocity(Point2D vectorVelocity) {
		this.vectorVelocity = vectorVelocity;
	}
	
}
