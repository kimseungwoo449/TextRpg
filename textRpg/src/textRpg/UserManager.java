package textRpg;

import java.util.ArrayList;

public class UserManager {
	public static int log;
	private static UserManager instance = new UserManager();
	private UnitManager unitManager = UnitManager.getInstance();
	private StageStore store = StageStore.getInstance();

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
			Color.redPrintln("동일한 아이디가 존재합니다.");
			return;
		}

		User user = new User(id, password);
		users.add(user);
		String info = String.format("가입 완료. %s님 TEXT RPG에 오신걸 환영합니다.", id);
		Color.greenPrintln(info);
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
			Color.redPrintln("ID 혹은 PASSWORD를 재확인 해주세요.");
			return;
		}

		users.remove(userIndex);
		Color.greenPrintln("탈퇴 완료.");
	}

	public void login() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");

		int userIndex = findUserIndexById(id);

		if (userIndex == -1 || !users.get(userIndex).getPassword().equals(password)) {
			Color.redPrintln("ID 혹은 PASSWORD를 재확인 해주세요.");
			return;
		}

		log = userIndex;
		Color.greenPrintln("로그인 완료.");
	}

	public void logout() {
		log = -1;
		Color.greenPrintln("로그아웃 완료.");
	}

	public void refinePassword() {
		String curPassword = GameManager.inputString("현재 PASSWORD");
		String newPassword = GameManager.inputString("새로운 PASSWORD");

		if (!users.get(log).getPassword().equals(curPassword)) {
			Color.redPrintln("비밀번호 재확인");
			return;
		}

		users.get(log).setPassword(newPassword);
		Color.greenPrintln("PASSWORD 수정 완료. 다시 로그인 해주세요.");
		log = -1;
	}

	public void buyHero() {
		Hero newHero = unitManager.buyHero();

		if (!users.get(log).buyHero(newHero)) {
			Color.redPrintln("소지금이 부족합니다.");
			return;
		}

		printBuyHeroMessage(newHero);

	}

	private void printBuyHeroMessage(Hero newHero) {
		if (newHero instanceof HeroWarrior)
			Color.cyanPrintln(printHero(newHero) + " 등장!");
		else if (newHero instanceof HeroWizard)
			Color.purplePrintln(printHero(newHero) + " 등장!");
		else if (newHero instanceof HeroPaladin)
			Color.bluePrintln(printHero(newHero) + " 등장!");
		else if (newHero instanceof HeroPrist)
			Color.yellowPrintln(printHero(newHero) + " 등장!");
	}

	private String printHero(Hero hero) {
		int grade = hero.getGrade();
		String star = grade == 1 ? "★" : grade == 2 ? "★★" : "★★★";

		String info = String.format("[%s] [%s]", star, hero.getName());
		return info;
	}

	public void sellHero() {
		User user = users.get(log);

		showMyHeroes();
		int sellIndex = GameManager.inputNumber("판매할 영웅")-1;

		if (!user.sellHero(sellIndex)) {
			Color.redPrintln("인덱스 재확인");
			return;
		}

		String info = String.format("판매완료. 현재 소지금 %d원", user.getCash());
		Color.greenPrintln(info);
	}

	public void showMyHeroes() {
		User user = users.get(log);
		user.showMyHero();
	}

	public void organizeParty() {
		User user = users.get(log);

		int[] heroIndex = new int[] { -1, -1, -1 };

		user.showMyHero();
		Color.greenPrintln("[파티의 인원은 3명입니다.]");
		for (int i = 0; i < heroIndex.length; i++) {
			int index = GameManager.inputNumber("선택 " + (i + 1)) - 1;

			if (index >= user.getMyHeroSize() || index < 0 || checkIndex(heroIndex, index)) {
				Color.redPrintln("인덱스 입력이 잘못되었거나, 이미 동일한 영웅이 선택되었습니다.");
				i--;
				continue;
			}

			heroIndex[i] = index;
		}
		user.addParty(heroIndex);

		Color.greenPrintln("파티 조직 완료.");
	}

	private boolean checkIndex(int[] heroIndex, int index) {
		for (int j = 0; j < heroIndex.length; j++) {
			if (heroIndex[j] == index)
				return true;
		}
		return false;
	}

	public void disassembleParty() {
		User user = users.get(log);

		showMyParties();
		int index = GameManager.inputNumber("해체할 파티의 인덱스");
		if (!user.deleteParty(index))
			Color.redPrintln("인덱스 재확인.");
		else
			Color.greenPrintln("파티 해체 완료.");
	}

	public void showMyParties() {
		User user = users.get(log);
		user.showParties();
	}
	
	public ArrayList<Unit> getParty(int index){
		return users.get(log).getParty(index);
	}
	
	public void buyItem() {
		User user = users.get(log);
		String userCashInfo = String.format("%s님의 현재 소지금 : %d원", user.getId(), user.getCash());
		Color.greenPrintln(userCashInfo);
		ArrayList<Item> jang = store.run();
		
		if (jang.size()==0) {
			return;
		}
		
		int totalPrice = 0;
		for (Item item : jang) {
			totalPrice += item.getPrice();
		}
		
		if (totalPrice>user.getCash()) {
			String info = String.format("소지금이 부족합니다. 현재 소지금 %d원", user.getCash());
			Color.redPrintln(info);
		} else {
			for (Item item : jang) {
				user.buyItem(item);
			}
			
			String info = String.format("구매 성공. 현재 소지금 %d원",user.getCash());
			Color.greenPrintln(info);
		}
	}
	
	public void showMyInventory() {
		User user = users.get(log);
		user.showInventory();
	}
	
	public Item getItem(int itmeIndex) {
		User user = users.get(log);
		return user.getItem( itmeIndex);
	}
	
	public void battle() {
		User user = users.get(log);
		user.showParties();
		int number = GameManager.inputNumber("사용할 파티 번호");
		ArrayList<Unit> party = user.getParty(number);
		
		if(party==null) {
			Color.redPrintln("파티 번호를 재확인 해주세요.");
			return;
		}
		
		ArrayList<Unit> monsters = unitManager.createMonsters();
		StageBattle battleStage = new StageBattle(party, monsters);
		battleStage.run();
	}
}
