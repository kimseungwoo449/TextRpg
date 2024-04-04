package textRpg;

import java.util.ArrayList;

public class UserManager {
	public static int log;
	private static UserManager instance = new UserManager();
	private ColorPrint color = ColorPrint.getInstance();
	private UnitManager unitManager = UnitManager.getInstance();

	private ArrayList<User> users;

	private UserManager() {
		log = -1;
		users = new ArrayList<User>();
	}

	public static UserManager getInstance() {
		return instance;
	}

	public void join() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");

		if (findUserIndexById(id) != -1) {
			color.redPrintln("동일한 아이디가 존재합니다.");
			return;
		}

		User user = new User(id, password);
		users.add(user);
		String info = String.format("가입 완료. %s님 TEXT RPG에 오신걸 환영합니다.", id);
		color.greenPrintln(info);
	}

	private int findUserIndexById(String id) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getId().equals(id))
				index = i;
		}
		return index;
	}

	public void leave() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");

		int userIndex = findUserIndexById(id);

		if (userIndex == -1 || !users.get(userIndex).getPassword().equals(password)) {
			color.redPrintln("ID 혹은 PASSWORD를 재확인 해주세요.");
			return;
		}

		users.remove(userIndex);
		color.greenPrintln("탈퇴 완료.");
	}

	public void login() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");

		int userIndex = findUserIndexById(id);

		if (userIndex == -1 || !users.get(userIndex).getPassword().equals(password)) {
			color.redPrintln("ID 혹은 PASSWORD를 재확인 해주세요.");
			return;
		}

		log = userIndex;
		color.greenPrintln("로그인 완료.");
	}

	public void logout() {
		log = -1;
		color.greenPrintln("로그아웃 완료.");
	}

	public void refinePassword() {
		String curPassword = GameManager.inputString("현재 PASSWORD");
		String newPassword = GameManager.inputString("새로운 PASSWORD");

		if (!users.get(log).getPassword().equals(curPassword)) {
			color.redPrintln("비밀번호 재확인");
			return;
		}

		users.get(log).setPassword(newPassword);
		color.greenPrintln("PASSWORD 수정 완료. 다시 로그인 해주세요.");
		log = -1;
	}

	public void buyHero() {
		Hero newHero = unitManager.buyHero();

		if (!users.get(log).buyHero(newHero)) {
			color.redPrintln("소지금이 부족합니다.");
			return;
		}

		printBuyHeroMessage(newHero);		
	}
	
	private void printBuyHeroMessage(Hero newHero) {
		if (newHero instanceof HeroWarrior)
			color.cyanPrintln(printHero(newHero)+" 등장!");
		else if (newHero instanceof HeroWizard)
			color.purplePrintln(printHero(newHero)+" 등장!");
		else if (newHero instanceof HeroPaladin)
			color.bluePrintln(printHero(newHero)+" 등장!");
		else if (newHero instanceof HeroPrist)
			color.yellowPrintln(printHero(newHero)+" 등장!");
	}
	
	private String printHero(Hero hero) {
		int grade = hero.getGrade();
		String star = grade == 1 ? "★" : grade == 2 ? "★★" : "★★★";
		
		String info = String.format("[%s] [%s]", star,hero.getName());
		return info;
	}
}
