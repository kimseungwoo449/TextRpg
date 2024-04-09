package textRpg;

enum Color {
	RESET("\u001B[0m"), BLACK("\u001B[30m"), RED("\u001B[31m"), GREEN("\u001B[32m"), YELLOW("\u001B[33m"),
	BLUE("\u001B[34m"), PURPLE("\u001B[35m"), CYAN("\u001B[36m"), WHITE("\u001B[37m");

	private String code;

	Color(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public static void redPrintln(String message) {
		System.out.println(Color.RED.getCode() + message + Color.RESET.getCode());
	}

	public static void greenPrintln(String message) {
		System.out.println(Color.GREEN.getCode() + message + Color.RESET.getCode());
	}

	public static void whitePrintln(String message) {
		System.out.println(Color.WHITE.getCode() + message + Color.RESET.getCode());
	}

	public static void bluePrintln(String message) {
		System.out.println(Color.BLUE.getCode() + message + Color.RESET.getCode());
	}

	public static void yellowPrintln(String message) {
		System.out.println(Color.YELLOW.getCode() + message + Color.RESET.getCode());
	}

	public static void cyanPrintln(String message) {
		System.out.println(Color.CYAN.getCode() + message + Color.RESET.getCode());
	}

	public static void purplePrintln(String message) {
		System.out.println(Color.PURPLE.getCode() + message + Color.RESET.getCode());
	}

	public static void redPrint(String message) {
		System.out.print(Color.RED.getCode() + message + Color.RESET.getCode());
	}

	public static void greenPrint(String message) {
		System.out.print(Color.GREEN.getCode() + message + Color.RESET.getCode());
	}

	public static void whitePrint(String message) {
		System.out.print(Color.WHITE.getCode() + message + Color.RESET.getCode());
	}

	public static void bluePrint(String message) {
		System.out.print(Color.BLUE.getCode() + message + Color.RESET.getCode());
	}

	public static void yellowPrint(String message) {
		System.out.print(Color.YELLOW.getCode() + message + Color.RESET.getCode());
	}

	public static void purplePrint(String message) {
		System.out.print(Color.PURPLE.getCode() + message + Color.RESET.getCode());
	}

	public static void cyanPrint(String message) {
		System.out.print(Color.CYAN.getCode() + message + Color.RESET.getCode());
	}
}

