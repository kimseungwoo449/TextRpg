package textRpg;

abstract public class Stage {
	private String stageName;
	
	public Stage(String stageName) {
		this.stageName = stageName;
	}
	
	abstract public void printMenu();
	
	public String getStageName() {
		return this.stageName;
	}
}
