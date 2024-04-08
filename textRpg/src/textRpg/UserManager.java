package textRpg;

import java.util.ArrayList;
import java.util.Map;

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
		int sellIndex = GameManager.inputNumber("판매할 영웅") - 1;

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

		user.showMyHero();
		Color.greenPrintln("[파티의 인원은 3명입니다.]");
		int[] heroIndex = choiceHeroes();
		user.addParty(heroIndex);

		Color.greenPrintln("파티 조직 완료.");
	}

	private int[] choiceHeroes() {
		int[] heroIndex = new int[] { -1, -1, -1 };
		User user = users.get(log);
		for (int i = 0; i < heroIndex.length; i++) {
			int index = GameManager.inputNumber("선택 " + (i + 1)) - 1;

			if (index >= user.getMyHeroSize() || index < 0 || checkIndex(heroIndex, index)) {
				Color.redPrintln("인덱스 입력이 잘못되었거나, 이미 동일한 영웅이 선택되었습니다.");
				i--;
				continue;
			}

			heroIndex[i] = index;
		}
		return heroIndex;
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

	public ArrayList<Unit> getParty(int index) {
		return users.get(log).getParty(index);
	}

	public void buyItem() {
		User user = users.get(log);
		String userCashInfo = String.format("%s님의 현재 소지금 : %d원", user.getId(), user.getCash());
		Color.greenPrintln(userCashInfo);
		ArrayList<Item> jang = store.run();

		if (jang.size() == 0) {
			return;
		}

		int totalPrice = 0;
		for (Item item : jang) {
			totalPrice += item.getPrice();
		}

		if (totalPrice > user.getCash()) {
			String info = String.format("소지금이 부족합니다. 현재 소지금 %d원", user.getCash());
			Color.redPrintln(info);
		} else {
			for (Item item : jang) {
				user.buyItem(item);
			}

			String info = String.format("구매 성공. 현재 소지금 %d원", user.getCash());
			Color.greenPrintln(info);
		}
	}

	public void showMyInventory() {
		User user = users.get(log);
		user.printInventory();
	}

	public void showConsumableInventory() {
		User user = users.get(log);
		Color.greenPrintln("--------- 인벤토리 ----------");
		user.viewConsumableItem();
	}

	public Item getItem(int itmeIndex) {
		User user = users.get(log);
		return user.getItem(itmeIndex);
	}

	public int getConsumableItemSize() {
		User user = users.get(log);
		return user.getConsumableItemSize();
	}

	public void battle() {
		User user = users.get(log);
		user.showParties();
		int number = GameManager.inputNumber("사용할 파티 번호");
		ArrayList<Unit> party = user.getParty(number);

		if (party == null) {
			Color.redPrintln("파티 번호를 재확인 해주세요.");
			return;
		}

		ArrayList<Unit> monsters = unitManager.createMonsters();
		StageBattle battleStage = new StageBattle(party, monsters);
		battleStage.run();
	}

	public String save() {
		String info = "";
		for (User user : users) {
			info += "userInfo/";
			info += user.makeUserInfo();
			info += "myHero/";
			info += user.makeMyHeroInfo();
			info += "consumableItem/";
			info += user.makeConsumableItemInfo();
			info += "equipableItem/";
			info += user.makeEquipableItemInfo();
			info += "parties/";
			info += user.makePartiesInfo();
		}
		if (info.length() > 0)
			info = info.substring(0, info.length() - 1);
		return info;
	}

	public void load(String info) {
		if (info.length() < 1)
			return;

		String[] dataArr = info.split("\n");
		int userCount = 0;

		for (int i = 0; i < dataArr.length; i += 5) {
			String[] userInfo = dataArr[i].split("/");
			loadUserInfo(userInfo);
			String[] myHeroInfo = dataArr[i + 1].split("/");
			loadMyHeroInfo(myHeroInfo, userCount);
			String[] consumableItemInfo = dataArr[i + 2].split("/");
			loadConsumableItemInfo(consumableItemInfo, userCount);
			String[] equipableItemInfo = dataArr[i + 3].split("/");
			loadEquipableItemInfo(equipableItemInfo, userCount);
			String[] partiesInfo = dataArr[i + 4].split("/");
			loadPartiesInfo(partiesInfo, userCount);
			userCount++;
		}
	}

	private void loadPartiesInfo(String[] partiesInfo, int userCount) {
		User user = users.get(userCount);
		for (int i = 1; i < partiesInfo.length; i++) {
			String temp[] = partiesInfo[i].split(",");
			int partyNumber = Integer.parseInt(temp[0]);
			int firstHero = Integer.parseInt(temp[1]);
			int secondHero = Integer.parseInt(temp[2]);
			int thirdHero = Integer.parseInt(temp[3]);
			user.setParties(partyNumber, firstHero, secondHero, thirdHero);
		}
	}

	private void loadEquipableItemInfo(String[] equipableItemInfo, int userCount) {
		ArrayList<Item> equipList = new ArrayList<Item>();
		for (int i = 1; i < equipableItemInfo.length; i++) {
			String[] temp = equipableItemInfo[i].split(",");
			String name = temp[0];
			int grade = Integer.parseInt(temp[1]);
			int value = Integer.parseInt(temp[2]);
			int heroIndex = Integer.parseInt(temp[3]);
			if (name.equals("무기")) {
				equipList.add(makeWeapon(grade, value, heroIndex));
			} else if (name.equals("방어구")) {
				equipList.add(makeArmor(grade, value, heroIndex));
			}
		}
		users.get(userCount).setEquipableItem(equipList);
	}

	private ItemWeapon makeWeapon(int grade, int value, int heroIndex) {
		ItemWeapon weapon = new ItemWeapon(grade);
		weapon.setExtraPower(value);
		weapon.setEquipedHeroIndex(heroIndex);
		return weapon;
	}

	private ItemArmor makeArmor(int grade, int value, int heroIndex) {
		ItemArmor armor = new ItemArmor(grade);
		armor.setArmor(value);
		armor.setEquipedHeroIndex(heroIndex);
		return armor;
	}

	private void loadConsumableItemInfo(String[] consumableItemInfo, int userCount) {
		ArrayList<Item> consumList = new ArrayList<Item>();
		for (int i = 1; i < consumableItemInfo.length; i++) {
			String[] temp = consumableItemInfo[i].split(",");
			String name = temp[0];
			int value = Integer.parseInt(temp[2]);
			if (name.equals("폭탄")) {
				consumList.add(makeBomb(value));
			} else if (name.equals("포션")) {
				consumList.add(makePotion(value));
			}
		}
		users.get(userCount).setConsumableItem(consumList);
	}

	private ItemPotion makePotion(int recoveryAmount) {
		ItemPotion potion = new ItemPotion();
		potion.setRecoveryAmount(recoveryAmount);
		;
		return potion;
	}

	private ItemBomb makeBomb(int damage) {
		ItemBomb bomb = new ItemBomb();
		bomb.setDamage(damage);
		return bomb;
	}

	private void loadUserInfo(String userInfo[]) {
		String info[] = userInfo[1].split(",");
		String id = info[0];
		String password = info[1];
		int cash = Integer.parseInt(info[2]);
		int partyNumber = Integer.parseInt(info[3]);

		User user = new User(id, password);
		user.setCash(cash);
		user.setPartyNumber(partyNumber);
		users.add(user);
	}

	private void loadMyHeroInfo(String[] myHeroInfo, int userCount) {
		Class<?>[] params = new Class<?>[] { int.class };
		ArrayList<Hero> heroList = new ArrayList<Hero>();
		for (int i = 1; i < myHeroInfo.length; i++) {
			String[] temp = myHeroInfo[i].split(",");
			int grade = Integer.parseInt(temp[0]);
			int lv = Integer.parseInt(temp[1]);
			String name = temp[2];
			String className = returnClassName(name);
			int extraPower = Integer.parseInt(temp[3]);
			int armor = Integer.parseInt(temp[4]);
			int maxHp = Integer.parseInt(temp[5]);
			int curHp = Integer.parseInt(temp[6]);
			int offensivePower = Integer.parseInt(temp[7]);
			int maxExp = Integer.parseInt(temp[8]);
			int exp = Integer.parseInt(temp[9]);

			String path = "textRpg.Hero" + className;
			try {
				Class<?> clazz = Class.forName(path);
				Object obj = clazz.getDeclaredConstructor(params).newInstance(grade);
				Hero hero = (Hero) obj;
				hero.loadAndSet(lv, extraPower, armor, maxHp, curHp, offensivePower, maxExp, exp);
				heroList.add(hero);
			} catch (Exception e) {
			}
		}
		users.get(userCount).setMyHero(heroList);
	}

	private String returnClassName(String name) {
		if (name.equals("전사"))
			return "Warrior";
		else if (name.equals("마법사"))
			return "Wizard";
		else if (name.equals("성직자"))
			return "Prist";
		else if (name.equals("성기사"))
			return "Paladin";
		return null;
	}

}
