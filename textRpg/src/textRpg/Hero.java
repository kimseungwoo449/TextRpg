package textRpg;

abstract public class Hero extends Unit {
	private int grade;
	private int lv;
	private int maxExp;
	private int extraPower;
	private int armor;

	public Hero(String name, int maxHp, int offensivePower, int grade) {
		super(name, maxHp, offensivePower, 0); // 이름, 최대 체력, 공격력, 경험치(0) 시작
		this.grade = grade;
		super.setMaxHp(grade);
		super.setOffensivePower(grade);
		this.lv = 1;
		this.maxExp = 100;
		this.armor = 0;
	}

	public void reciveExp(int exp) {
		super.setExp(exp);
	}

	public String checkLevelUp() {
		String info = null;
		if (super.getExp() >= maxExp) {
			this.lv += 1;
			super.levelUp(maxExp);
			this.maxExp *= 1.5;
			info = String.format("%s[%s] Level Up!", stringOfThisGradeAndLevel(), this.getName());
		}
		return info;
	}

	public int getGrade() {
		return this.grade;
	}

	public int getLv() {
		return this.lv;
	}

	public int getExtraPower() {
		return this.extraPower;
	}

	public void setExtraPower(int extraPower) {
		this.extraPower = extraPower;
	}

	public int getArmor() {
		return this.armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getMaxExp() {
		return this.maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public void loadAndSet(int lv, int extraPower, int armor, int maxHp, int cupHp, int offensivePower, int maxExp,
			int exp) {
		super.loadAndSet(maxHp, cupHp, offensivePower, exp);
		this.lv = lv;
		this.extraPower = extraPower;
		this.armor = armor;
		this.maxExp = maxExp;

	}

	private String stringOfThisGradeAndLevel() {
		return String.format("[%s][Lv : %d]", this.grade == 1 ? "★" : this.grade == 2 ? "★★" : "★★★", this.lv);
	}

	@Override
	public String toString() {
		String info = stringOfThisGradeAndLevel();
		info += super.toString();
		info += String.format(" [EXP : %d/%d]", super.getExp(), maxExp);
		info += String.format("%s%s", this.extraPower != 0 ? " [추가공격력 : " + this.extraPower + "]" : "",
				this.armor != 0 ? " [방어력 : " + this.armor + "]" : "");
		return info;
	}

}
