package textRpg;

public class MonsterOrc extends Monster{
	public MonsterOrc(int maxHp, int offensivePower, int exp) {
		super("오크", maxHp, offensivePower, exp);
	}

	@Override
	public int skill(Unit target) {
		int attack = this.getOffensivePower()*2;
		return attack;
	}
}
