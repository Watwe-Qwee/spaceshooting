package logic.effect;

import java.lang.Thread.State;

import application.AudioUtility;
import javafx.scene.canvas.GraphicsContext;
import logic.entity.Player;
import sharedObject.RenderableHolder;

public class BuffEffect extends Effect{

	private Player player;
	private boolean draw;
	
	public BuffEffect(Player player) {
		super();
		this.player=player;
		this.draw=false;
		this.width=100;
		this.height=100;
		this.thread=new Thread() {
			public void run() {
				player.setBuffed(true);
				for(int i=0;i<5;i++) {
					draw=true;
					try {
						this.sleep((long) (((Math.exp(-i/2)))*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					draw=false;
					try {
						this.sleep((long) ((1-(Math.exp(-i/2)))*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				destroy();
				player.setBuffed(false);
			}
		};
		this.thread.start();
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(draw&&!this.thread.getState().equals(State.TERMINATED)&&player.getHP()>0) {
			this.center=player.getCenter();
			this.calPosition();
			gc.drawImage(RenderableHolder.buffedEffect, getX(), getY(), this.width, this.height);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return vissible;
	}

}
