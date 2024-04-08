package textRpg;

import java.util.Random;

public class ItemArmor extends Item implements Equipable {
	private Random ran = new Random();

	private int grade;
	private int armor;
	private int equipedHeroIndex;

	public ItemArmor(int grade) {
		setNameAndPrice(grade);
		this.equipedHeroIndex = -1;
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

	@Override
	public void fucntion(Unit target) {
		Hero hero = (Hero) target;
		if (this.equipedHeroIndex == -1) {
			hero.setArmor(armor);
		} else {
			hero.setArmor(0);
		}
	}

	public int getGrade() {
		return this.grade;
	}

	public int getArmor() {
		return this.armor;
	}

	@Override
	public String toString() {
		String info = String.format("%s 방어력 : %d", super.toString(), this.armor);
		return info;
	}

	@Override
	public void setEquipedHeroIndex(int heroIndex) {
		this.equipedHeroIndex = heroIndex;
	}
	
	@Override
	public int getEquipedHeroIndex() {
		return this.equipedHeroIndex;
	}

}
