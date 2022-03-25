package logic.effect;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public abstract class Effect implements IRenderable{
	protected int z;
	protected Thread thread;
	protected Point2D position,center;
	protected boolean destroyed,vissible;
	protected double width,height;
	public Effect() {
		this.z=2;
		destroyed=false;
		vissible=true;
	}
	public void destroy() {
		destroyed=true;
		vissible=false;
	}
	public void calPosition() {
		this.position=center.subtract(new Point2D(width/2, height/2));
	}
	public double getX() {
		return this.position.getX();
	}
	public double getY() {
		return this.position.getY();
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
	}
	public Point2D getCenter() {
		return center;
	}
	public void setCenter(Point2D center) {
		this.center = center;
	}
	public boolean isDestroyed() {
		return destroyed;
	}
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	public boolean isVissible() {
		return vissible;
	}
	public void setVissible(boolean vissible) {
		this.vissible = vissible;
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
	
}
