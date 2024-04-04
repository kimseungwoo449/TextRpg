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

	public void levelUp() {
		this.exp -= 100;
		this.maxHp += this.maxHp / 3;
		this.curHp = this.maxHp;
		this.offensivePower += this.offensivePower / 3;
	}

	@Override
	public String toString() {
		String info = String.format("[%s] [%d/%d] [%d]", name, curHp, maxHp, offensivePower);
		return info;
	}
}
