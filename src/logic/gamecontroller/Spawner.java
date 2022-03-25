package logic.gamecontroller;

import java.util.ArrayList;

import application.Updatable;
import logic.entity.Asteroid;
import logic.entity.BuffObject;
import logic.entity.BulletBuffObject;
import logic.entity.HeartBuffObject;
import logic.entity.Villain;

public class Spawner implements Updatable{
	private GameController gameController;
	private ArrayList<Villain> villains;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<BuffObject> buffObjects;
	public Spawner(GameController gameController) {
		this.gameController=gameController;
		this.villains=gameController.getVillains();
		this.asteroids=gameController.getAsteroids();
		this.buffObjects=gameController.getBuffObjects();
	}
	public void update() {
		asteroidSpawner();
		buffSpawner();
		villainSpawner();
	}
	private void asteroidSpawner() {
		if((Math.random()<0.005&&asteroids.size()<2+gameController.getScore()/500)||(asteroids.size()==0)) {
			gameController.addNewObject(new Asteroid(gameController.getPlayer()));
		}
	}
	private void buffSpawner() {
		if(Math.random()<0.005&&buffObjects.size()<2) {
			if(Math.random()<0.5)
				gameController.addNewObject(new HeartBuffObject());
			else
				gameController.addNewObject(new BulletBuffObject());
		}
	}
	private void villainSpawner() {
		if((Math.random()<0.005&&villains.size()<2+gameController.getScore()/200)||(villains.size()==0)) {
			Villain temp=new Villain();
			int i=0;
			while(i<villains.size()){
				if(targetNotInBound(temp,villains.get(i))) {
						i++;
				}
				else {
					temp.randomNewTarget();
					i=0;
				}
			}
			gameController.addNewObject(temp);
		}
	}
	private boolean targetNotInBound(Villain villain,Villain checker) {
		boolean x=((villain.getTarget().getX()<checker.getTarget().getX()+checker.getWidth())&&(villain.getTarget().getX()+villain.getWidth()>checker.getTarget().getX())),
				y=((villain.getTarget().getY()<checker.getTarget().getY()+checker.getHeight())&&(villain.getTarget().getY()+villain.getHeight()>checker.getTarget().getY()));
		return !(x&&y);
	}
}
