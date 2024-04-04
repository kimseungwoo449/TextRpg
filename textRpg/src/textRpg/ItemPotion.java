package textRpg;

import java.util.Random;

public class ItemPotion extends Item{
	private Random ran = new Random();
	private int recoveryAmount;
	
	public ItemPotion() {
		super("포션", 200);
		this.recoveryAmount = ran.nextInt(20)+180;	//180~200 회복량
	}

	@Override
	public void fucntion(Unit target) {
		target.setCurHp(recoveryAmount);
	}
	
	public int getRecoveryAmount() {
		return this.recoveryAmount;
	}

}
