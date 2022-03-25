package logic.gamecontroller;

import java.util.ArrayList;
import java.util.List;

import application.AudioUtility;
import application.KeyUtility;
import application.Updatable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.effect.BuffEffect;
import logic.effect.Effect;
import logic.effect.FireEffect;
import logic.entity.Asteroid;
import logic.entity.BuffObject;
import logic.entity.Bullet;
import logic.entity.BulletBuffObject;
import logic.entity.Entity;
import logic.entity.HeartBuffObject;
import logic.entity.Player;
import logic.entity.PlayerBullet;
import logic.entity.Villain;
import logic.entity.VillainBullet;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameController implements Updatable{
	private List<Entity> entities;
	private List<Effect> effects;
	private Spawner spawner;
	
	private int score;
	private Player player;
	private ArrayList<PlayerBullet> playerBullets;
	private ArrayList<VillainBullet> villainBullets;
	private ArrayList<Villain> villains;
	private ArrayList<Asteroid> asteroids;
	private ArrayList<BuffObject> buffObjects;
	
	public GameController() {
		score=0;
		player=new Player();
		this.effects=new ArrayList<Effect>();
		this.entities = new ArrayList<Entity>();
		this.playerBullets=new ArrayList<>();
		this.villainBullets=new ArrayList<>();
		this.asteroids=new ArrayList<Asteroid>();
		this.buffObjects=new ArrayList<BuffObject>();
		this.villains=new ArrayList<Villain>();
		spawner=new Spawner(this);
		this.addNewObject(player);
	}
	
	public void addNewObject(Entity entity){
		entities.add(entity);
		RenderableHolder.getInstance().add(entity);
		if(entity instanceof Villain) {
			villains.add((Villain) entity);
		}
		else if(entity instanceof Asteroid) {
			asteroids.add((Asteroid) entity);
		}
		else if(entity instanceof BuffObject) {
			buffObjects.add((BuffObject) entity);
		}
		else if(entity instanceof VillainBullet) {
			villainBullets.add((VillainBullet) entity);
		}
		else if(entity instanceof PlayerBullet) {
			playerBullets.add((PlayerBullet) entity);
		}
	}
	public void addNewObject(Effect effect) {
		effects.add(effect);
		RenderableHolder.getInstance().add(effect);
	}
	
	public void update() {
		spawner.update();
		playerController();
		for(Entity entity:entities) {
			entity.update();
		}
		for(Villain villain:villains) {
			if(villain.getShootingDelay()<=0) {
				addNewObject(villain.shoot());
				AudioUtility.villainShootingSFX.play();
			}
		}
		collideCheckker();
		removeDestroyed();
	}
	private void removeDestroyed() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
			{
				entities.remove(i);
			}
		}
		for (int i = villains.size() - 1; i >= 0; i--) {
			if (villains.get(i).isDestroyed())
			{
				addNewObject(new FireEffect(villains.get(i)));
				score+=50;
				villains.remove(i);
			}
		}
		for(int i=asteroids.size()-1;i>=0;i--) {
			if (asteroids.get(i).isDestroyed())
			{
				if(asteroids.get(i).getHP()<=0)
					addNewObject(new FireEffect(asteroids.get(i)));
				score+=50*(1-asteroids.get(i).getHP()/100);
				asteroids.remove(i);
			}
		}
		for (int i = playerBullets.size() - 1; i >= 0; i--) {
			if (playerBullets.get(i).isDestroyed())
				playerBullets.remove(i);
		}
		for (int i = villainBullets.size() - 1; i >= 0; i--) {
			if (villainBullets.get(i).isDestroyed())
				villainBullets.remove(i);
		}
		for (int i = buffObjects.size() - 1; i >= 0; i--) {
			if (buffObjects.get(i).isDestroyed())
				buffObjects.remove(i);
		}
		for (int i = effects.size() - 1; i >= 0; i--) {
			if (effects.get(i).isDestroyed())
				effects.remove(i);
		}
	}
	private void collideCheckker() {
		for(BuffObject buffobject:buffObjects) {
			if(buffobject.collideWith(player)) {
				buffobject.buff(player);
				buffobject.destroy();
				addNewObject(new BuffEffect(player));
				AudioUtility.shootingStarSFX.play();
			}
		}
		for(Asteroid asteroid:asteroids) {
			if(asteroid.collideWith(player)) {
				asteroid.damage(player);
				AudioUtility.collisionSFX.play();
			}
		}
		for(Bullet bullet:villainBullets) {
			if(bullet.collideWith(player)) {
				bullet.damage(player);
				AudioUtility.collisionSFX.play();
			}
		}
		for(Bullet bullet:playerBullets) {
			for(Villain villain:villains)
				if(bullet.collideWith(villain)) {
					bullet.damage(villain);
					AudioUtility.collisionSFX.play();
				}
		}
		for(Bullet bullet:playerBullets) {
			for(Asteroid asteroid:asteroids)
				if(bullet.collideWith(asteroid)) {
					bullet.damage(asteroid);
					AudioUtility.collisionSFX.play();
				}
		}
	}

	private void playerController() {
		if(KeyUtility.getKeyCollector().contains(KeyUtility.getMoveLeft())&&!player.dumpingEdgeLeft()) {
			player.setVectorVelocity(new Point2D(-1, 0));
		}
		else if(KeyUtility.getKeyCollector().contains(KeyUtility.getMoveRight())&&!player.dumpingEdgeRight()) {
			player.setVectorVelocity(new Point2D(1, 0));
		}
		else {
			player.setVectorVelocity(new Point2D(0, 0));
		}
		if(KeyUtility.getKeyCollector().contains(KeyUtility.getShooting())&&player.getShootingDelay()==0) {
			addNewObject(player.shoot());
			AudioUtility.shootingSFX.play();
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public ArrayList<Villain> getVillains() {
		return villains;
	}

	public void setVillains(ArrayList<Villain> villains) {
		this.villains = villains;
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	public void setAsteroids(ArrayList<Asteroid> asteroids) {
		this.asteroids = asteroids;
	}

	public ArrayList<BuffObject> getBuffObjects() {
		return buffObjects;
	}

	public void setBuffObjects(ArrayList<BuffObject> buffObjects) {
		this.buffObjects = buffObjects;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<PlayerBullet> getPlayerBullets() {
		return playerBullets;
	}

	public void setPlayerBullets(ArrayList<PlayerBullet> playerBullets) {
		this.playerBullets = playerBullets;
	}

	public ArrayList<VillainBullet> getVillainBullets() {
		return villainBullets;
	}

	public void setVillainBullets(ArrayList<VillainBullet> villainBullets) {
		this.villainBullets = villainBullets;
	}

}
