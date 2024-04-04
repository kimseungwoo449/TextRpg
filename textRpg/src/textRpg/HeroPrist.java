package textRpg;

public class HeroPrist extends Hero{

	public HeroPrist(int grade) {
		super("성직자", 450, 50, grade);
	}

	@Override
	public int skill(Unit target) {
		int myOffensivePower = super.getOffensivePower();
		int heal = myOffensivePower*4;
		target.setCurHp(heal);
		return heal;
	}
	
}
