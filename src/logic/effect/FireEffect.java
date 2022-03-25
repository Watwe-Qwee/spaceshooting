package logic.effect;

import java.lang.Thread.State;

import application.AudioUtility;
import javafx.scene.canvas.GraphicsContext;
import logic.entity.Entity;
import sharedObject.RenderableHolder;

public class FireEffect extends Effect {
	private int state;
	public FireEffect(Entity entity) {
		// TODO Auto-generated constructor stub
		super();
		this.width=100;this.height=100;
		this.center=entity.getCenter();
		this.calPosition();
		this.thread=new Thread() {
			public void run() {
				AudioUtility.explosionSFX.play();
				for(int i=0;i<9;i++) {
					if(state<8) {
						state++;
					}
					try {
						sleep(450);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				destroy();
			}
		};
		thread.start();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(state<8&&!this.thread.getState().equals(State.TERMINATED))
			gc.drawImage(RenderableHolder.fireEffect.get(state),getX(),getY(),width,height);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		if(this.thread.getState().equals(State.TERMINATED)) {
			this.destroyed=true;
			this.vissible=false;
		}
		return this.destroyed;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return this.vissible;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
