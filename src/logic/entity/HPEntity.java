package logic.entity;

public abstract class HPEntity extends CollidableEntity {
	protected double HP;
	
	protected HPEntity() {
		super();
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}
	
	public boolean isDestroyed(){
		if(this.HP<0)
		{
			this.destroyed=true;
			this.visible=false;
		}
		return this.destroyed;
	}
	
}
