package textRpg;

public class MonsterBat extends Monster {
	public MonsterBat(int maxHp, int offensivePower, int exp) {
		super("박쥐", maxHp, offensivePower, exp);
	}

	@Override
	public int skill(Unit target) {
		int attack = (int) (this.getOffensivePower() * 1.2);
		int heal = attack * 2;
		this.setCurHp(heal);
		return attack;
	}
}
