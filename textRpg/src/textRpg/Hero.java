package textRpg;

abstract public class Hero extends Unit{
	private int grade;
	private int lv;
	
	public Hero(String name, int maxHp, int offensivePower, int grade) {
		super(name, maxHp, offensivePower, 0);	// 이름, 최대 체력, 공격력, 경험치(0) 시작
		this.grade = grade;
		super.setMaxHp(grade);
		super.setOffensivePower(grade);
		this.lv = 1;
	}
	
	public void reciveExp(int exp) {
		super.setExp(exp);
	}
	
	public void checkLevelUp() {
		if (super.getExp() >= 100) {
			this.lv += 1;
			super.levelUp();
		}
	}

}
