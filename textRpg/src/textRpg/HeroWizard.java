package textRpg;

public class HeroWizard extends Hero{

	public HeroWizard( int grade) {
		super("마법사", 600, 50, grade);
	}

	@Override
	public int skill(Unit target) {
		int myOffensivePower = super.getOffensivePower();
		int attack = myOffensivePower + (int) (myOffensivePower * 1.5);
		return attack;
	}

}
