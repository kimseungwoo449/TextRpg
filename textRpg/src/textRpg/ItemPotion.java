package textRpg;

import java.util.Random;

public class ItemPotion extends Item implements Consumable {
	private Random ran = new Random();
	private int recoveryAmount;

	public ItemPotion() {
		super("포션", 200);
		this.recoveryAmount = ran.nextInt(20) + 180; // 180~200 회복량
	}

	public int getRecoveryAmount() {
		return this.recoveryAmount;
	}
	
	public void setRecoveryAmount(int recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}

	@Override
	public void fucntion(Unit target) {
		target.setCurHp(recoveryAmount);
	}

	@Override
	public String toString() {
		String info = super.toString() + String.format(" 회복량 : %d ", this.recoveryAmount);
		return info;
	}
}
