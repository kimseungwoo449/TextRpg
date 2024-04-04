package textRpg;

public class HeroWarrior extends Hero {
	private boolean isSkilled;
	
	
	public HeroWarrior(int grade) {
		super("전사", 1000, 65, grade);
	}
	
	@Override
	public int skill(Unit target) {
		if(isSkilled) {
			return 0;
		}		
		this.isSkilled = true;
		return 1;
	}
	
	public boolean isSkilled() {
		return this.isSkilled;
	}
	
	public void setIsSkilled() {
		this.isSkilled = this.isSkilled == false ? true : false;
	}
	
}
