package textRpg;

public class ItemPotion extends Item{
	private int recoveryAmount;
	
	public ItemPotion(int recoveryAmonunt) {
		super("포션", 200);
		this.recoveryAmount = recoveryAmonunt;
	}

	@Override
	public void fucntion(Unit target) {
		target.setCurHp(recoveryAmount);
	}
	
	public int getRecoveryAmount() {
		return this.recoveryAmount;
	}

}
