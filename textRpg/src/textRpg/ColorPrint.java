package textRpg;

public class ColorPrint {
	public final String RESET = "\u001B[0m";
	public final String BLACK = "\u001B[30m";
	public final String RED = "\u001B[31m";
	public final String GREEN = "\u001B[32m";
	public final String YELLOW = "\u001B[33m";
	public final String BLUE = "\u001B[34m";
	public final String PURPLE = "\u001B[35m";
	public final String CYAN = "\u001B[36m";
	public final String WHITE = "\u001B[37m";
	public final String BLACK_BACKGROUND = "\u001B[40m";
	public final String RED_BACKGROUND = "\u001B[41m";
	public final String GREEN_BACKGROUND = "\u001B[42m";
	public final String YELLOW_BACKGROUND = "\u001B[43m";
	public final String BLUE_BACKGROUND = "\u001B[44m";
	public final String PURPLE_BACKGROUND = "\u001B[45m";
	public final String CYAN_BACKGROUND = "\u001B[46m";
	public final String WHITE_BACKGROUND = "\u001B[47m";

	private static ColorPrint instance = new ColorPrint();

	private ColorPrint() {

	}

	public static ColorPrint getInstance() {
		return instance;
	}

	public void redPrint(String message) {
		System.out.println(RED + message + RESET);
	}

	public void greenPrint(String message) {
		System.out.println(GREEN + message + RESET);
	}

	public void whitePrint(String message) {
		System.out.println(WHITE + message + RESET);
	}

	public void bluePrint(String message) {
		System.out.println(BLUE + message + RESET);
	}
	
	public void yellowPrint(String message) {
		System.out.println(YELLOW + message + RESET);
	}
	
	public void purplePrint(String message) {
		System.out.println(PURPLE + message + RESET);
	}
}
