package textRpg;

abstract public class Stage {
	private String stageName;
	
	public Stage(String stageName) {
		this.stageName = stageName;
	}

	public String getStageName() {
		return this.stageName;
	}
	
	abstract public void printMenu();
}
