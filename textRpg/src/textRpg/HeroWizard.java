package textRpg;

public class HeroWizard extends Hero {

	public HeroWizard(int grade) {
		super("마법사", 500, 50, grade);
	}

	@Override
	public int skill(Unit target) {
		int myOffensivePower = super.getOffensivePower();
		myOffensivePower = super.getExtraPower();
		int attack = myOffensivePower + (int) (myOffensivePower * 1.5);
		return attack;
	}

}
