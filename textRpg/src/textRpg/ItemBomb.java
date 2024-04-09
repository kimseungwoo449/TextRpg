package textRpg;

import java.util.Random;

public class ItemBomb extends Item implements Consumable {
	private Random ran = new Random();
	private int damage;

	public ItemBomb() {
		super("폭탄", 700);
		this.damage = ran.nextInt(20) + 200; // 200~220데미지
	}

	public int getDamage() {
		return this.damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void fucntion(Unit target) {
		target.takeDamage(this.damage);
	}

	@Override
	public String toString() {
		String info = super.toString() + String.format(" 데미지 : %d ", this.damage);
		return info;
	}
}
