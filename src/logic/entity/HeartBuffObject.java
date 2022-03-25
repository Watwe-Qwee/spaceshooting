package logic.entity;

import sharedObject.RenderableHolder;

public class HeartBuffObject extends BuffObject{
	
	public HeartBuffObject() {
		super();
		this.image=RenderableHolder.heartBuffObject;
	}

	@Override
	public void buff(Player player) {
		// TODO Auto-generated method stub
		super.buff(player);
		player.setHP(100);
	}

}
