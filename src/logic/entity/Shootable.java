package logic.entity;

import java.util.ArrayList;

public interface Shootable {
	public static final int SHOOTING_DELAY=25;
	public static final int SHOOTING_DELAY2=75;
	public abstract Bullet shoot();
	public abstract void countdown();
	public abstract double getBulletDamage();
}
