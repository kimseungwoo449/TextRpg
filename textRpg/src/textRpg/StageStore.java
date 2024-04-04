package textRpg;

public class StageStore extends Stage {
	private static StageStore instance = new StageStore();
	private final int BOMB = 1;
	private final int POTION = 2;

	private StageStore() {
		super("상점");
	}

	public static StageStore getInstance() {
		return instance;
	}

	@Override
	public void printMenu() {
		Color.greenPrintln("---------- " + this.getStageName() + " ----------");
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 폭탄 [700원]");
		Color.greenPrintln("[2] 포션 [200원]");
	}

	public Item buyItem() {
		Item item = null;

		printMenu();
		int itemNumber = GameManager.inputNumber("구매할 아이템");
		item = makeItem(itemNumber);
		return item;
	}

	private Item makeItem(int itemNumber) {
		Item item = null;
		if (itemNumber == BOMB)
			item = new ItemBomb();
		else if (itemNumber == POTION)
			item = new ItemPotion();

		return item;
	}

}
