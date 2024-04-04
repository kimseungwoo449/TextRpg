package textRpg;

public class MonsterWolf extends Monster{
	
	public MonsterWolf( int maxHp, int offensivePower, int exp) {
		super("늑대", maxHp, offensivePower, exp);
	}

	@Override
	public int skill(Unit target) {
		int attack = (int)(this.getOffensivePower()*1.5);
		return attack;
	}
}
