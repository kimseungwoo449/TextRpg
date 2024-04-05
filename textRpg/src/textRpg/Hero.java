package textRpg;

abstract public class Hero extends Unit {
	private int grade;
	private int lv;
	private int maxExp;

	public Hero(String name, int maxHp, int offensivePower, int grade) {
		super(name, maxHp, offensivePower, 0); // 이름, 최대 체력, 공격력, 경험치(0) 시작
		this.grade = grade;
		super.setMaxHp(grade);
		super.setOffensivePower(grade);
		this.lv = 1;
		this.maxExp = 100;
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

	private String stringOfThisGradeAndLevel() {
		return String.format("[%s][Lv : %d]", this.grade == 1 ? "★" : this.grade == 2 ? "★★" : "★★★", this.lv);
	}

	@Override
	public String toString() {
		String info = stringOfThisGradeAndLevel();
		info += super.toString();
		info += String.format(" [EXP : %d/%d]", super.getExp(), maxExp);
		return info;
	}

}
