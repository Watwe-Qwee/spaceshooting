package logic.entity;

import sharedObject.RenderableHolder;

public class BulletBuffObject extends BuffObject{

	public BulletBuffObject() {
		super();
		this.image=RenderableHolder.bulletBuffObject;
	}
	@Override
	public void buff(Player player) {
		// TODO Auto-generated method stub
		super.buff(player);
		player.setBulletDamage(player.getBulletDamage()*1.1);
	}

}
