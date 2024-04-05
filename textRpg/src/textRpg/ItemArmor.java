package textRpg;

import java.util.Random;

public class ItemArmor extends Item implements Equipable {
	private Random ran = new Random();

	private int grade;
	private boolean isEquiped;
	private int armor;

	public ItemArmor(int grade) {
		setNameAndPrice(grade);
	}

	@Override
	public void setNameAndPrice(int grade) {
		if (grade < 1 || grade > 3)
			return;

		if (grade == 1) {
			super.setName("가죽 갑옷");
			super.setPrice(600);
			this.armor = ran.nextInt(5) + 10;
		} else if (grade == 2) {
			super.setName("철 갑옷");
			super.setPrice(800);
			this.armor = ran.nextInt(5) + 15;
		} else if (grade == 3) {
			super.setName("다이아몬드 갑옷");
			super.setPrice(1100);
			this.armor = ran.nextInt(5) + 20;
		}
	}

	public boolean isEquied() {
		return this.isEquiped;
	}

	@Override
	public void fucntion(Unit target) {
		Hero hero = (Hero) target;
		if (!isEquiped) {
			hero.setArmor(armor);
			this.isEquiped = true;
		} else {
			hero.setArmor(0);
			this.isEquiped = false;
		}
	}

}
