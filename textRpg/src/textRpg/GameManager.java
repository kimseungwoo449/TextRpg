package textRpg;

import java.util.Scanner;

public class GameManager {
	public static Scanner sc = new Scanner(System.in);
	
	public static int inputNumber(String message) {
		int number = -1;
		try {
			Color.whitePrint(message+" : ");
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			Color.redPrintln("숫자로 입력 해주세요.");
		}
		return number;
	}
	
	public static String inputString(String message) {
		Color.whitePrint(message+" : ");
		return sc.next();
	}
	
	public void test() {
		
	}
}
