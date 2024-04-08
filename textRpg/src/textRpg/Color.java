package textRpg;

public class Color {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";

	public static void redPrintln(String message) {
		System.out.println(RED + message + RESET);
	}

	public static void greenPrintln(String message) {
		System.out.println(GREEN + message + RESET);
	}

	public static void whitePrintln(String message) {
		System.out.println(WHITE + message + RESET);
	}

	public static void bluePrintln(String message) {
		System.out.println(BLUE + message + RESET);
	}
	
	public static void yellowPrintln(String message) {
		System.out.println(YELLOW + message + RESET);
	}
	
	public static void cyanPrintln(String message) {
		System.out.println(CYAN + message + RESET);
	}
	
	public static void purplePrintln(String message) {
		System.out.println(PURPLE + message + RESET);
	}
	
	public static void redPrint(String message) {
		System.out.print(RED + message + RESET);
	}

	public static void greenPrint(String message) {
		System.out.print(GREEN + message + RESET);
	}

	public static void whitePrint(String message) {
		System.out.print(WHITE + message + RESET);
	}

	public static void bluePrint(String message) {
		System.out.print(BLUE + message + RESET);
	}
	
	public static void yellowPrint(String message) {
		System.out.print(YELLOW + message + RESET);
	}
	
	public static void purplePrint(String message) {
		System.out.print(PURPLE + message + RESET);
	}
	
	public static void cyanPrint(String message) {
		System.out.print(CYAN + message + RESET);
	}
}
