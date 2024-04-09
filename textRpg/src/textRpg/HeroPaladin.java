package textRpg;

public class HeroPaladin extends Hero {
	public HeroPaladin(int grade) {
		super("성기사", 800, 55, grade);
	}

	@Override
	public int skill(Unit target) {
		int myOffensivePower = super.getOffensivePower();
		myOffensivePower += super.getExtraPower();
		int heal = myOffensivePower * 3;
		super.setCurHp(heal);
		return heal;
	}
}
