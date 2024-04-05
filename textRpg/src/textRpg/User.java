package textRpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private final int CONSUMABLE_ITEM = 1;
	private final int EQUIPABLE_ITEM = 2;

	private final int EQUIP = 1;
	private final int DISARM = 2;

	private String id;
	private String password;

	private int cash;
	private int partyNumber;

	private Map<Integer, ArrayList<Unit>> parties;
	private ArrayList<Hero> myHero;

	private ArrayList<Item> consumableItem;
	private ArrayList<Item> equipableItem;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.parties = new HashMap<Integer, ArrayList<Unit>>();
		this.myHero = initialHeros();
		this.consumableItem = new ArrayList<Item>();
		this.equipableItem = new ArrayList<Item>();
		this.cash += 10000;
		this.partyNumber = 1;
	}

	public String getId() {
		return this.id;
	}

	public int getCash() {
		return this.cash;
	}

	public int getMyHeroSize() {
		return this.myHero.size();
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private ArrayList<Hero> initialHeros() {
		ArrayList<Hero> temp = new ArrayList<Hero>();
		String[] className = { "HeroWarrior", "HeroWizard", "HeroPaladin", "HeroPrist" };
		Class<?>[] pram = new Class<?>[] { int.class };

		for (int i = 0; i < className.length; i++) {
			String path = "textRpg." + className[i];
			try {
				Class<?> clazz = Class.forName(path);
				Object obj = clazz.getDeclaredConstructor(pram).newInstance(1);
				Hero hero = (Hero) obj;
				temp.add(hero);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public boolean buyHero(Hero hero) {
		if (cash < 500)
			return false;
		this.myHero.add(hero);
		cash -= 500;
		return true;
	}

	public boolean sellHero(int index) {
		if (index < 0 || index >= myHero.size())
			return false;

		Hero targetHero = this.myHero.get(index);
		int grade = targetHero.getGrade();

		cash += 300 * grade;
		this.myHero.remove(index);
		deleteHeroInParty(targetHero);
		return true;
	}

	private void deleteHeroInParty(Hero targetHero) {
		for (int i = 1; i < partyNumber; i++) {
			ArrayList<Unit> party = parties.get(i);

			for (int j = 0; j < party.size(); j++) {
				Unit heroInParty = party.get(j);
				if (heroInParty.equals(targetHero)) {
					party.remove(targetHero);
					break;
				}
			}
		}
	}

	public void showMyHero() {
		for (int i = 0; i < myHero.size(); i++) {
			Hero hero = myHero.get(i);

			if (hero instanceof HeroWarrior)
				Color.cyanPrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroWizard)
				Color.purplePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPaladin)
				Color.bluePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPrist)
				Color.yellowPrintln((i + 1) + " : " + hero);
		}
	}

	public void addParty(int[] indexArray) {
		ArrayList<Unit> party = new ArrayList<Unit>();

		for (int i = 0; i < indexArray.length; i++) {
			int heroIndex = indexArray[i];
			Hero targetHero = myHero.get(heroIndex);
			party.add(targetHero);
		}

		this.parties.put(partyNumber++, party);
	}

	public boolean deleteParty(int index) {
		if (index < 1 || index > parties.size())
			return false;
		for (int i = 1; i <= parties.size(); i++) {
			if (i > index) {
				parties.put(i - 1, parties.get(i));
			}
			if (i == parties.size())
				parties.remove(i);
		}
		return true;
	}

	public void showParties() {
		List keySet = new ArrayList(parties.keySet());
		Collections.sort(keySet);
		viewParty(keySet);
	}

	private void viewParty(List keySet) {
		for (int i = 0; i < parties.size(); i++) {
			int key = (int) keySet.get(i);
			ArrayList<Unit> party = parties.get(key);

			Color.greenPrintln("------------------ " + key + " ------------------");
			for (int j = 0; j < party.size(); j++) {
				Unit hero = party.get(j);

				if (hero instanceof HeroWarrior)
					Color.cyanPrintln(hero + "");
				else if (hero instanceof HeroWizard)
					Color.purplePrintln(hero + "");
				else if (hero instanceof HeroPaladin)
					Color.bluePrintln(hero + "");
				else if (hero instanceof HeroPrist)
					Color.yellowPrintln(hero + "");

			}
		}
	}

	public boolean buyItem(Item item) {
		int pay = item.getPrice();
		if (pay > cash)
			return false;
		cash -= pay;

		if (item instanceof Equipable)
			equipableItem.add(item);
		else if (item instanceof Consumable)
			consumableItem.add(item);

		return true;
	}

	public void printInventory() {
		Color.greenPrintln("--------- 인벤토리 ----------");
		Color.greenPrintln("[1] 소비 아이템");
		Color.greenPrintln("[2] 장비 아이템");
		int select = GameManager.inputNumber("Menu");
		runPrintInventory(select);
	}

	private void runPrintInventory(int select) {
		if (select == CONSUMABLE_ITEM)
			viewConsumableItem();
		else if (select == EQUIPABLE_ITEM)
			viewEquipableItem();
	}

	private void viewConsumableItem() {
		int number = 1;
		for (Item item : consumableItem) {
			Color.cyanPrintln(number++ + ". " + item);
		}
	}

	private void viewEquipableItem() {
		int number = 1;
		for (Item item : equipableItem) {
			String info = String.format("%d. %s %s", number++, item, item.isEquiped() ? "[장착중]" : "");
			Color.cyanPrintln(info);
		}
		Color.greenPrintln("[1] 장착");
		Color.greenPrintln("[2] 해제");
		Color.greenPrintln("[*] 뒤로가기");
		int select = GameManager.inputNumber("Menu");
		runEquipableItem(select);
	}

	private void runEquipableItem(int select) {
		if (select == EQUIP)
			equip();
		else if (select == DISARM)
			disarm();
	}

	private void equip() {
		int itemIndex = GameManager.inputNumber("장착할 아이템 번호") - 1;
		if (itemIndex < 0 || itemIndex >= equipableItem.size() || this.equipableItem.get(itemIndex).isEquiped()) {
			Color.redPrintln("번호를 다시 확인해 주세요.");
			return;
		}
		Item item = this.equipableItem.get(itemIndex);

		showMyHero();
		int heroIndex = GameManager.inputNumber("장착할 영웅 번호") - 1;

		if (heroIndex < 0 || heroIndex >= myHero.size()) {
			Color.redPrintln("번호를 다시 확인하세요.");
			return;
		}

		Hero hero = myHero.get(heroIndex);
		if ((hero.getArmor() != 0 && item instanceof ItemArmor)
				|| (hero.getExtraPower() != 0 && item instanceof ItemWeapon)) {
			Color.redPrintln("해당 영웅은 이미 장착 중인 아이템 종류입니다.");
			return;
		}

		item.fucntion(hero);
		Color.greenPrintln("장착완료.");
	}

	private void disarm() {
		int itemIndex = GameManager.inputNumber("장착할 아이템 번호") - 1;
		if (itemIndex < 0 || itemIndex >= equipableItem.size() || !this.equipableItem.get(itemIndex).isEquiped()) {
			Color.redPrintln("번호를 다시 확인해 주세요.");
			return;
		}
		Item item = this.equipableItem.get(itemIndex);
		item.fucntion(item.getEquipedHero());
		Color.greenPrintln("해제완료.");
	}

	public Item getItem(int itemIndex) {
		if (itemIndex < 0 || itemIndex >= consumableItem.size())
			return null;
		Item item = consumableItem.get(itemIndex);
		consumableItem.remove(itemIndex);
		return item;
	}

	public ArrayList<Unit> getParty(int index) {
		if (index < 1 || index >= partyNumber)
			return null;

		return parties.get(index);
	}

}
