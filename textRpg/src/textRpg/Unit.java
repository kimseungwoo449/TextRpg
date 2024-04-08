package textRpg;

abstract public class Unit {
	private int maxHp;
	private int curHp;
	private int offensivePower;
	private String name;
	private boolean isDead;
	private int exp;

	public Unit(String name, int maxHp, int offensivePower, int exp) {
		this.name = name;
		this.maxHp = maxHp;
		this.curHp = this.maxHp;
		this.offensivePower = offensivePower;
		this.isDead = false;
		this.exp = exp;
	}

	public void attack(Unit target, int attack) {
		target.curHp -= attack;
		checkIsDead(target);
	}

	private void checkIsDead(Unit target) {
		if (target.curHp <= 0) {
			target.curHp = 0;
			target.isDead = true;
		}
	}

	public void takeDamage(int attack) {
		this.curHp -= attack;

		checkIsDead(this);
	}

	public String getName() {
		return this.name;
	}

	public int getExp() {
		return this.exp;
	}
	
	public void setExp(int exp) {
		this.exp += exp;
	}

	public int getOffensivePower() {
		return this.offensivePower;
	}

	public void setOffensivePower(int grade) {
		this.offensivePower = set(grade, this.offensivePower);
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getCurHp() {
		return this.curHp;
	}
	
	private int set(int grade, int myAbility) {
		if (grade == 2)
			myAbility *= 1.5;
		else if (grade == 3)
			myAbility *= 2;
		return myAbility;
	}

	public void setMaxHp(int grade) {
		this.maxHp = set(grade, this.maxHp);
		this.curHp = this.maxHp;
	}

	abstract public int skill(Unit target);

	public void setCurHp(int heal) {
		if (curHp + heal > maxHp) {
			this.curHp = this.maxHp;
			return;
		}

		curHp += heal;
	}

	public boolean isDead() {
		return this.isDead;
	}

	public void levelUp(int maxExp) {
		this.exp -= maxExp;
		this.maxHp += this.maxHp / 3;
		this.curHp = this.maxHp;
		this.offensivePower += this.offensivePower / 3;
	}

	@Override
	public String toString() {
		String info = String.format("[%s] [HP : %d/%d] [POWER : %d]", name, curHp, maxHp, offensivePower);
		return info;
	}
}
