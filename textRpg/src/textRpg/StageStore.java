package textRpg;

import java.util.ArrayList;

public class StageStore extends Stage {
	private static StageStore instance = new StageStore();
	private final int CONSUMABLE = 1;
	private final int EQUIPABLE = 2;
	private final int CANCLE = 3;
	private final int BUY = 0;

	private final int BOMB = 1;
	private final int POTION = 2;

	private final int WEAPON = 1;
	private final int ARMOR = 2;

	private boolean isRun;

	private ArrayList<Item> jang;

	private StageStore() {
		super("상점");
	}

	public static StageStore getInstance() {
		return instance;
	}

	@Override
	public void printMenu() {
		if (jang.size() > 0) {
			int totalPrice = 0;
			Color.bluePrintln("------ 장바구니 ------");
			for (int i = 0; i < jang.size(); i++) {
				Item item = jang.get(i);
				Color.bluePrintln((i + 1) + ". " + item + " : " + item.getPrice() + "원");
				totalPrice += item.getPrice();
			}
			Color.cyanPrintln("총액 : " + totalPrice + "원");
		}
		Color.greenPrintln("---------- " + this.getStageName() + " ----------");
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 소비 아이템");
		Color.greenPrintln("[2] 장비 아이템");
		Color.greenPrintln("[3] 장바구니에서 빼기");
		Color.greenPrintln("[0] 구매");
	}

	private void runMenu(int select) {
		if (select == CONSUMABLE)
			printConsumable();
		else if (select == EQUIPABLE)
			printEquipable();
		else if (select == CANCLE)
			cancleItem();
		else if (select == BUY)
			this.isRun = false;
	}

	private void printConsumable() {
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 폭탄 [700원]");
		Color.greenPrintln("[2] 포션 [200원]");
		Color.greenPrintln("[*] 뒤로가기");
		int choice = GameManager.inputNumber("Menu");
		buyConsumableItem(choice);
	}

	private void buyConsumableItem(int itemNumber) {
		Item item = null;
		if (itemNumber == BOMB)
			item = new ItemBomb();
		else if (itemNumber == POTION)
			item = new ItemPotion();
		else
			return;
		jang.add(item);
	}

	private void printEquipable() {
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 무기");
		Color.greenPrintln("[2] 방어구");
		Color.greenPrintln("[*] 뒤로가기");
		int choice = GameManager.inputNumber("Menu");
		printEquipableSubmenu(choice);
	}

	private void printEquipableSubmenu(int choice) {
		if (choice == WEAPON)
			printWeapons();
		else if (choice == ARMOR)
			printArmors();
	}

	private void printWeapons() {
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 돌 검 [500원]");
		Color.greenPrintln("[2] 철 검 [700원]");
		Color.greenPrintln("[3] 다이아몬드 검 [1000원]");
		Color.greenPrintln("[*] 뒤로가기");
		int choice = GameManager.inputNumber("Menu");
		buyWeapon(choice);
	}

	private void buyWeapon(int choice) {
		ItemWeapon weapon = new ItemWeapon(choice);
		if (weapon.getName() == null)
			return;

		jang.add(weapon);
	}

	private void printArmors() {
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 가죽 갑옷 [600원]");
		Color.greenPrintln("[2] 철 갑옷 [800원]");
		Color.greenPrintln("[3] 다이아몬드 갑옷 [1100원]");
		Color.greenPrintln("[*] 뒤로가기");
		int choice = GameManager.inputNumber("Menu");
		buyArmor(choice);
	}

	private void buyArmor(int choice) {
		ItemArmor armor = new ItemArmor(choice);
		if (armor.getName() == null)
			return;

		jang.add(armor);
	}

	private void cancleItem() {
		Color.bluePrintln("------ 장바구니 ------");
		for (int i = 0; i < jang.size(); i++) {
			Item item = jang.get(i);
			Color.bluePrintln((i + 1) + ". " + item + " : " + item.getPrice() + "원");
		}
		int number = GameManager.inputNumber("아이템 번호(뒤로가기를 원하면 범위 밖의 번호입력)") - 1;

		if (number < 0 || number >= jang.size())
			return;

		jang.remove(number);
	}

	public ArrayList<Item> run() {
		this.isRun = true;
		this.jang = new ArrayList<Item>();
		while (isRun) {
			printMenu();
			int select = GameManager.inputNumber("Menu");
			runMenu(select);
		}

		return jang;
	}
}
