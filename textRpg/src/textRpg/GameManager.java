package textRpg;

import java.util.Scanner;

public class GameManager {
	public ColorPrint color = ColorPrint.getInstance();
	public static Scanner sc = new Scanner(System.in);
	
	public static int inputNumber(String message) {
		ColorPrint color = ColorPrint.getInstance();
		int number = -1;
		try {
			color.whitePrint(message+" : ");
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			color.redPrintln("숫자로 입력 해주세요.");
		}
		return number;
	}
	
	public static String inputString(String message) {
		ColorPrint color = ColorPrint.getInstance();
		color.whitePrint(message+" : ");
		return sc.next();
	}
	
	public void test() {
		
	}
}
