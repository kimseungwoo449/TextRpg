package textRpg;

public class ItemBomb extends Item{
	private int damage;
	
	public ItemBomb(int damage) {
		super("폭탄", 700);
		this.damage = damage;
	}

	@Override
	public void fucntion(Unit target) {
		target.takeDamage(this.damage);
	}
	
	public int getDamage() {
		return this.damage;
	}
	
}
